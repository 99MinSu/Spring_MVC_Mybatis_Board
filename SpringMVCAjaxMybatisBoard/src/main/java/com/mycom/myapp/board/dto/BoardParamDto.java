package com.mycom.myapp.board.dto;

// 등록, 수정은 BoardParamDto 대신 BoardDto 를 이용한다.
public class BoardParamDto {
	// 목록
	private int limit;
	private int offset;
	private String searchWord;
	
	// 상세
	private int boardId;
	private int userSeq; // 현재 사용자 useSeq
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	@Override
	public String toString() {
		return "BoardParamDto [limit=" + limit + ", offset=" + offset + ", searchWord=" + searchWord + ", boardId="
				+ boardId + ", userSeq=" + userSeq + "]";
	}

	
	
}
