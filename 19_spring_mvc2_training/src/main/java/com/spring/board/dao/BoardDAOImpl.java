package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDTO;

@Repository // Data Access Object(데이터 접근 객체는) Repository를 명시하여야 한다.
			// 현재 클래스를 DAO bean으로 등록시킨다.
//dao는 매퍼랑 연결용도
// 뷰로 넘기는건 return

public class BoardDAOImpl implements BoardDAO {
	/*
	sqlSession.insert();
	sqlSession.update();
	sqlSession.delete();
	sqlSession.select();
	sqlSession.selectList();
	*/
	@Autowired
	private SqlSession sqlSession;//여기 클래스에서만 쓸거니까 프라이빗으로 만듬
	
	@Override
	public void insert(BoardDTO bdto) throws Exception {
						   // 매퍼 파일의 namepsace명.id명            , 매개변수
		sqlSession.insert("com.spring.mapper.BoardMapper.insertBoard" , bdto);
	}//(매퍼 파일 네임스페이스.ID , 넘어가야할 매개변수) 2개

	@Override
	public List<BoardDTO> selectAll() throws Exception {
		return sqlSession.selectList("com.spring.mapper.BoardMapper.getAllBoard");
	}//매개변수는 무조건 1,2 개만 가능 매퍼에꽂을때는 매개변수 받아서 2개

	
	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return sqlSession.selectOne("com.spring.mapper.BoardMapper.getOneBoard" , num);
	}

	
	@Override
	public BoardDTO validateUserCheck(BoardDTO bdto) throws Exception {
		return sqlSession.selectOne("com.spring.mapper.BoardMapper.validateUserCheck" , bdto);
	}

	@Override
	public void increaseReadCount(int num) throws Exception {
		sqlSession.update("com.spring.mapper.BoardMapper.increaseReadCount" , num);
	}

	@Override
	public void update(BoardDTO bdto) throws Exception {
		sqlSession.update("com.spring.mapper.BoardMapper.updateBoard" , bdto);
	}

	
	@Override
	public void delete(int num) throws Exception {

		sqlSession.delete("com.spring.mapper.BoardMapper.deleteBoard" , num);
	}

	
}




