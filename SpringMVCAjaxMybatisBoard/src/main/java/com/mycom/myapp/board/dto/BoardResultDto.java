package com.mycom.myapp.board.dto;

import java.util.List;

public class BoardResultDto {
	// 요청결과
	private String result;
	// 목록
	private List<BoardDto> list;
	// 상세
	private BoardDto dto;
	// 글 전체 수
	private int count;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<BoardDto> getList() {
		return list;
	}
	public void setList(List<BoardDto> list) {
		this.list = list;
	}
	public BoardDto getDto() {
		return dto;
	}
	public void setDto(BoardDto dto) {
		this.dto = dto;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
}
