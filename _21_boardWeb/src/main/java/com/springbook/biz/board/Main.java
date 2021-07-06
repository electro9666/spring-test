package com.springbook.biz.board;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext-2.xml");
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		BoardVO vo = new BoardVO();
		vo.setTitle("제목1");
		vo.setWriter("작가1");
		vo.setContent("임시 내용");
		
		boardService.insertBoard(vo);
		
		container.close();
	}
}
