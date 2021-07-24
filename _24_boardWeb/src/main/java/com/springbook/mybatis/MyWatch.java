package com.springbook.mybatis;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.function.Consumer;

public class MyWatch {
	private boolean isStop = false;
	// 프로젝트 경로
	private String[] projPath; 

	private WatchKey watchKey;

	public MyWatch(String[] projPath) {
		this.projPath = projPath;
	}
	
	public void start(Consumer<Path> f) throws IOException {
		this.isStop = false;
		// watchService 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 경로 생성
		for(String p : projPath) {
			Path path = Paths.get(p);
			// 해당 디렉토리 경로에 와치서비스와 이벤트 등록
//			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW);
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		}

		Thread thread = new Thread(() -> {
			while (!isStop) {
				try {
					watchKey = watchService.take();// 이벤트가 오길 대기(Blocking)
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				List<WatchEvent<?>> events = watchKey.pollEvents();// 이벤트들을 가져옴
				
//				System.out.println("events:" + events.size());
				for (WatchEvent<?> event : events) {
					// 이벤트 종류
					Kind<?> kind = event.kind();
					// 경로
					Path paths = (Path) event.context();
//					System.out.println("paths.toFile().length():" + paths.toFile().length());
					System.out.println("changed: " + paths.toAbsolutePath());// C:\...\...\test.txt
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						System.out.println("created something in directory");
					} else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
						System.out.println("delete something in directory");
					} else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
						// TODO
						System.out.println("watch: modified something in directory");
						f.accept(paths);
					} else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
						System.out.println("overflow");
					} else {
						System.out.println("invalid even type.");
					}
				}
				if (!watchKey.reset()) {
					try {
						watchService.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						System.err.println("watchService를 종료합니다.");
					}
				}
			}
		});
		thread.start();
	}
	public void stop() {
		this.isStop = true;
	}
}
