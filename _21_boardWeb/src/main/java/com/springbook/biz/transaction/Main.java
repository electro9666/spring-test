package com.springbook.biz.transaction;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.transaction.board.BoardService;
import com.springbook.biz.transaction.board.BoardVO;

public class Main {
	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext-4.xml");
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		BoardVO vo = new BoardVO();
		vo.setSeq(10);
		vo.setTitle("제목4");
		vo.setWriter("작가1");
		vo.setContent("임시 내용");
		
		boardService.insertBoard(vo);
		
		container.close();
	}
}
