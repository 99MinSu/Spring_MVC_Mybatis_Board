package com.mycom.myapp.board.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.board.dto.BoardDto;
import com.mycom.myapp.board.dto.BoardParamDto;
import com.mycom.myapp.board.dto.BoardResultDto;
import com.mycom.myapp.board.service.BoardService;
import com.mycom.myapp.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boards")
public class BoardController {
	
	//BoardService DI
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/list") // limit, offset parameter 를 포함하는 요청 또는 searchWord 를 추가로 포함하는 요청
	@ResponseBody
	private BoardResultDto listBoard(BoardParamDto boardParamDto) {
		// searchWord 포함 여부에 따라 분리
		BoardResultDto boardResultDto;
		
		if( Strings.isEmpty(boardParamDto.getSearchWord())) { // 검색어가 있는 경우
			boardResultDto = boardService.listBoard(boardParamDto);
		}else {
			boardResultDto = boardService.listBoardSearchWord(boardParamDto); // 검색어가 없는 경우
		}			
		return boardResultDto;
	}
	
	@GetMapping("/detail/{boardId}") 
	@ResponseBody	
	private BoardResultDto detailBoard(@PathVariable int boardId, HttpSession session) {
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);
		int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq(); // login 할 때 session 에 담은 객체
		boardParamDto.setUserSeq(userSeq);
		return boardService.detailBoard(boardParamDto);
	}
	
	@PostMapping("/insert")
	@ResponseBody
	private BoardResultDto insertBoard(BoardDto boardDto, HttpSession session) {
		
		int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq(); // login 할 때 session 에 담은 객체
		boardDto.setUserSeq(userSeq);
		return boardService.insertBoard(boardDto);
	}
	
	@PostMapping("/update")
	@ResponseBody
	private BoardResultDto updateBoard(BoardDto boardDto) {
		return boardService.updateBoard(boardDto);
	}
	
	@GetMapping("/delete/{boardId}") 
	@ResponseBody	
	private BoardResultDto deleteBoard(@PathVariable int boardId) {
		return boardService.deleteBoard(boardId);
	}
}
