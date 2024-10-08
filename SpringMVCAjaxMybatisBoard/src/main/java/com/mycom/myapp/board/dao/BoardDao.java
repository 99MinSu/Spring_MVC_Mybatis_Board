package com.mycom.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	List<BoardDto> listBoard(BoardParamDto boardParamDto); // limit, offset
	int listBoardTotalCount(); // 전체 건수
	
	List<BoardDto> listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
	int listBoardSearchWordTotalCount(BoardParamDto boardParamDto); // 검색어 건수
	
	BoardDto detailBoard(BoardParamDto boardParamDto);
	
	int insertBoard(BoardDto boardDto);
	int updateBoard(BoardDto boardDto);
	int deleteBoard(int boardId);
	
	// 조회수 처리
	int countBoardUserRead(BoardParamDto boardParamDto);
	int insertBoardUserRead(@Param("boardId") int boardId, @Param("userSeq") int userSeq);
	int updateBoardReadCount(int boardId);
}
