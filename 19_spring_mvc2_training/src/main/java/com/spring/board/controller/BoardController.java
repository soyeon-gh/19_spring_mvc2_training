package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.board.dto.BoardDTO;
import com.spring.board.service.BoardService;

@Controller	// 컨트롤러임을 명시 (controller bean에 등록시킴)
public class BoardController {

	@Autowired							// DI(의존성 주입) : BoardService 객체를 스프링에서 생성하여 주입시킴
	private BoardService boardService;  // Service 로직을 호출하기 위한 객체 생성
	
	
	@RequestMapping(value="/")
	public String main() {
		return "board/main";
	}
	
	//----------------------------------------------------------------------------
	
	// value : url 주소를 명시
	// method : 요청타입을 명시
	@RequestMapping(value="/boardWrite" , method=RequestMethod.GET)
	public String boardWrite() {
		return "board/bWrite"; // servlet-context.xml에 명시된 대로 포워딩할 jsp파일을 작성해 준다.
	}
	
	
	@RequestMapping(value="/boardWrite" , method=RequestMethod.POST)
	public String boardWrite(BoardDTO bdto) throws Exception {
		
		boardService.insertBoard(bdto);
		
		return "board/main";
	}
	
	//----------------------------------------------------------------------------
	
	@RequestMapping(value="/boardList")
	public String boardList(Model model) throws Exception {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		
		// 메서드의 매개변수에 Model 인터페이스를 선언하고
		// model.addAttribute(키,벨류) 형태로 데이터를 view로 전송한다.
		
		model.addAttribute("boardList" , boardList);
		
		return "board/bList";
	}
	
	//----------------------------------------------------------------------------
	
	@RequestMapping(value="/boardInfo")
	public String boardInfo(@RequestParam("num") int num , Model model) throws Exception{
		
		BoardDTO bdto = boardService.getOneBoard(num);
		
		model.addAttribute("bdto" , bdto);
		return "board/bInfo";
	
	}
	
	//----------------------------------------------------------------------------
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.GET)
	public String boardUpdate(@RequestParam("num") int num , Model model) throws Exception {
		
		BoardDTO bdto = boardService.getOneBoard(num);
		model.addAttribute("bdto" , bdto);
		
		return "board/bUpdate";
		
	}
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.POST)
	public String boardUpdate(BoardDTO bdto , Model model) throws Exception {
		
		boolean isSucceed = boardService.updateBoard(bdto);
		
		if (isSucceed)	model.addAttribute("success" , true);
		else            model.addAttribute("success" , false);
		
		return "board/bUpdatePro";
		
	}
	
	//----------------------------------------------------------------------------
	
	@RequestMapping(value="/boardDelete" , method=RequestMethod.GET)
	public String boardDelete(@RequestParam("num") int num , Model model) throws Exception {
		
		BoardDTO bdto = boardService.getOneBoard(num);
		
		model.addAttribute("bdto",bdto);
		
		return "board/bDelete";
	}
	
	@RequestMapping(value="/boardDelete" , method=RequestMethod.POST)
	public String boardDelete(BoardDTO bdto, Model model) throws Exception {
		
		//지우는 서비스!
		
		boolean isSucceed = boardService.deleteBoard(bdto);
		
		if (isSucceed)	model.addAttribute("success" , true);
		else            model.addAttribute("success" , false);
		return "board/bDeletePro";
	}
	
	
	
	
	
	
	
}


