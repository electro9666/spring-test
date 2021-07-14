package com.springbook.biz.springJdbc;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.springJdbc.board.BoardService;
import com.springbook.biz.springJdbc.board.BoardVO;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext-3.xml");
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		BoardVO vo = new BoardVO();
		vo.setTitle("제목2");
		vo.setWriter("작가1");
		vo.setContent("임시 내용");
		
		boardService.insertBoard(vo);
		
		container.close();
	}
}
