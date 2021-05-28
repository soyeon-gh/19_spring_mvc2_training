package com.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.board.dto.BoardDTO;
import com.spring.board.service.BoardService;

@Controller  //컨트롤러임을 명시 ( controller bean에 등록시킴 )
public class BoardController {
	
	@Autowired
	private BoardService boardService; 
	// DI(의존성 주입) : BoardService 객체를 스프링에서 생성하여 주입시킴
	// Service 로직을 호출하기 위한 객체 생성
	
	// value : url 주소를 명시
	// method : 요청 타입을 명시
	@RequestMapping(value="/boardWrite", method=RequestMethod.GET)
	public String boardWrite() {
		return "board/bWrite"; // servlet-context.xml에 명시된 대로 포워딩할 jsp파일을 작성해 준다.
		
	}
	
	@RequestMapping(value="/boardWrite", method=RequestMethod.POST)
	public String boardWrite(BoardDTO bdto) throws Exception {
		boardService.insertBoard(bdto);
		return "board/main";
	}
	
	@RequestMapping("/boardList")
	public String boardList(Model model) throws Exception{
		
		List<BoardDTO> boardList = boardService.getBoardList();
		// 메소드의 매개변수에 Model 인터페이스를 선언하고
		// model.addAttribute(키, 벨류) 형태로 데이터를  view로 전송한다.
		model.addAttribute("boardList", boardList);
		return "board/bList";
	}
	
}
