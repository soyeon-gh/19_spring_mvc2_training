
package com.spring.board_advance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.board_advance.dao.BoardAdvanceDAO;
import com.spring.board_advance.dto.BoardAdvanceDTO;

@Service		
public class BoardAdvanceServiceImpl implements BoardAdvanceService {

	@Inject				
	private BoardAdvanceDAO boardDAO;

	// 내용 반환
	@Override
	public List<BoardAdvanceDTO> getSearchBoard(Map<String, Object> searchInfo) throws Exception{
		return boardDAO.getSearchBoard(searchInfo);
	}
	 
	// 하나의 게시글 조회
	
	@Override
	public BoardAdvanceDTO getOneBoard(int num) throws Exception{
		boardDAO.increaseReadCount(num); // 조회수
		return boardDAO.getOneBoard(num);
	}
	
	
	// 게시글 작성
	@Override
	public void insertBoard(BoardAdvanceDTO bdto) throws Exception {
		boardDAO.insertBoard(bdto);
	}

	
	// 게시글 수정
	@Override
	public boolean updateBoard(BoardAdvanceDTO bdto) throws Exception {
		boolean isSuccess = false;
		if (boardDAO.validateUserCheck(bdto) != null) {
			isSuccess = true;
			boardDAO.updateBoard(bdto);
		}
		return isSuccess;
	}

	
	// 게시글 삭제
	@Override
	public boolean deleteBoard(BoardAdvanceDTO bdto) throws Exception {
		boolean isSuccess = false;
		if (boardDAO.validateUserCheck(bdto) != null) {
			boardDAO.deleteBoard(bdto.getNum());
			isSuccess = true;
		}
		return isSuccess;
	}

	
	// 더미데이타 만들기
	@Override
	public void makeDummyData() throws Exception {
		
		Random ran = new Random();
		
		List<BoardAdvanceDTO> dummyDataList = new ArrayList<BoardAdvanceDTO>();
	
		String[] word = {"가","나","다","라","마","바","사","아","자","차","카","타","파","하"};
		
		BoardAdvanceDTO temp = null;
		String writer;
		String password;
		String subject;
		String email;
		String content;
		
		for (int i = 100; i < 300; i++) {
			
			writer    = "";
			password  = "1111";
			subject   = "";
			email     = "";
			content   = "";
			for (int j = 0; j < 7; j++) {
				writer  += word[ran.nextInt(word.length)];
				subject += word[ran.nextInt(word.length)];
				content += word[ran.nextInt(word.length)];
				if (j < 4) {
					email += word[ran.nextInt(word.length)];
				}
			}
			email += "@gmail.com";
			
			temp = new BoardAdvanceDTO();
			temp.setNum(i);		
			temp.setWriter(writer);
			temp.setEmail(email);
			temp.setSubject(subject);
			temp.setPassword(password);
			temp.setRef(i);
			temp.setReLevel(1);
			temp.setReStep(1);
			temp.setReadCount(0);
			temp.setContent(content);
			
			dummyDataList.add(temp);
			
		}
		
		boardDAO.makeDummyData(dummyDataList);
		
	}

	
	// 개수 반환
	@Override
	public int getAllBoardCount(Map<String, String> searchCountInfo) throws Exception {
		return boardDAO.getAllBoardCount(searchCountInfo);
	}

	
	// 댓글 작성
	@Override
	public void insertReplyBoard(BoardAdvanceDTO bdto) throws Exception {
		boardDAO.updateBoardReplyStep(bdto);       // 댓글 업뎃(수정)
		boardDAO.insertReplyBoard(bdto);			// 댓글 작성
	}
	
}
