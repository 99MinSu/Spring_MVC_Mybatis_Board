package com.mycom.myapp.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myapp.board.dao.BoardDao;
import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;

// 서비스 layer 는 복잡한 Business Logic 을 처리하는 영역, 사용자 정의 오류, 시스템 오류
// 예외 처리?? 다양한 예외 처리 (사용자 정의 포함) 를 통해서 보다 구체적인 처리가 가능
@Service
public class BoardServiceImpl implements BoardService{

	// BoardDao DI
	private final BoardDao boardDao;
	
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
		
	}
	@Override
	public BoardResultDto listBoard(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardDto> list = boardDao.listBoard(boardParamDto);
			int count = boardDao.listBoardTotalCount();
			
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult("success");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}

		return boardResultDto;
	}
	
	@Override
	public BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardDto> list = boardDao.listBoardSearchWord(boardParamDto);
			int count = boardDao.listBoardSearchWordTotalCount(boardParamDto);
			
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult("success");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			BoardDto boardDto = boardDao.detailBoard(boardParamDto);
			// 글쓴이와 보는이가 같은 지 여부
			if( boardDto.getUserSeq() == boardParamDto.getUserSeq()) { // controller 에서 session 으로부터 얻어서 보내준다.
				boardDto.setSameUser(true);
			}else {
				boardDto.setSameUser(false);
			}
			boardResultDto.setDto(boardDto);
			boardResultDto.setResult("success");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}

		return boardResultDto;
	}
	@Override
	public BoardResultDto insertBoard(BoardDto boardDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.insertBoard(boardDto);
			if( ret == 1) {
				boardResultDto.setResult("success");
			}else
				boardResultDto.setResult("fail");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}

		return boardResultDto;
	}
	@Override
	public BoardResultDto updateBoard(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.updateBoard(boardDto);
			if( ret == 1) {
				boardResultDto.setResult("success");
			}else
				boardResultDto.setResult("fail");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}

		return boardResultDto;
	}
	@Override
	public BoardResultDto deleteBoard(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int ret = boardDao.deleteBoard(boardId);
			if( ret == 1) {
				boardResultDto.setResult("success");
			}else
				boardResultDto.setResult("fail");
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult("fail");
		}

		return boardResultDto;
	}

}
