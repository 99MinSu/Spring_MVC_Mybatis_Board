package com.mycom.myapp.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
		
		// GlobalExceptionHandler 테스트 코드
//		String s = null;
//		s.length();
		
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
	@Transactional
	public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// 조회수
			int userReadCnt = boardDao.countBoardUserRead(boardParamDto);
			System.out.println("boardId : " + boardParamDto.getBoardId());
			System.out.println("userSeq : " + boardParamDto.getUserSeq());
			System.out.println("userReadCnt " + userReadCnt);
			
			if( userReadCnt == 0) { // 현 사용자가 현 게시글을 처음 조회
				// board_user_read 에 insert (현 게시글, 현 사용자)
				boardDao.insertBoardUserRead(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
				
				// transaction test
//				String s = null;
//				s.length();
				
				// 현재 게시글의 조회 수를 +1
				boardDao.updateBoardReadCount(boardParamDto.getBoardId());
			}
			// #3 예외 발생 없이 테스트
			// 아래의 코드는 예외가 발생하지 않아도 무조건 rollback
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
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
			
			// #1 RuntimeException 객체를 상위 호출자에게 전달
			// 정상적인 process 흐름이 방해된다.
//			throw new RuntimeException("detailBoard() error");
			
			// #2 TransactionAspectSupport
			// 정상적인 process 흐름이 진행된다.
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
