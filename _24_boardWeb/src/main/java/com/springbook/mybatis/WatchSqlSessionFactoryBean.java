package com.springbook.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

public class WatchSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {
	private MyWatch myWatch = null;
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private SqlSessionFactory proxy;
	private Resource[] mapperLocations;

	public void setMapperLocations(Resource[] mapperLocations) {
		super.setMapperLocations(mapperLocations);
		this.mapperLocations = mapperLocations;
		System.out.println("this.mapperLocations" + this.mapperLocations);
	}

	/**
	 * 싱글톤 멤버로 SqlMapClient 원본 대신 프록시로 설정하도록 오버라이드.
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();

		// init
		proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
				new Class[] { SqlSessionFactory.class }, new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// log.debug("method.getName() : " + method.getName());
						return method.invoke(getParentObject(), args);
					}
				});

		HashSet<String> pathList = new HashSet<>();
		for (int i = 0; i < mapperLocations.length; i++) {
//			System.out.println("mappingLocation:" + mappingLocation.getFile().getParentFile());
			pathList.add(mapperLocations[i].getFile().getParentFile().getAbsolutePath());
		}
//		System.out.println("pathList:" + pathList);

		// start watchService
		myWatch = new MyWatch(pathList.toArray(new String[] {}));
		myWatch.start(a -> {
//			System.out.println("!!" + a);
			lock.writeLock().lock();
			try {
				super.afterPropertiesSet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			} finally {
				lock.writeLock().unlock();
			}
		});
	}

	private Object getParentObject() throws Exception {
		lock.readLock().lock();
		try {
			return super.getObject();
		} finally {
			lock.readLock().unlock();
		}
	}

	public SqlSessionFactory getObject() {
		return this.proxy;
	}

	public Class<? extends SqlSessionFactory> getObjectType() {
		return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);
	}

	public boolean isSingleton() {
		return true;
	}

	public void destroy() throws Exception {
		System.out.println("destroy12");
		myWatch.stop();
	}
}