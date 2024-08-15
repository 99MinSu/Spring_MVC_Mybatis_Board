package com.mycom.myapp.board.service;


import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

public interface BoardService {
	BoardResultDto listBoard(BoardParamDto boardParamDto); // limit, offset
	BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
	// service layer 에서는 총 건수 가져오는 부분 분리 X
	
	BoardResultDto detailBoard(BoardParamDto boardParamDto);
	
	BoardResultDto insertBoard(BoardDto boardDto);
	BoardResultDto updateBoard(BoardDto boardDto);
	BoardResultDto deleteBoard(int boardId);

}
