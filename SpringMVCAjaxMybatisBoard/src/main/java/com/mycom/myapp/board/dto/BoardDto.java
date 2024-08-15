package com.mycom.myapp.board.dto;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;
public class BoardDto {
    private int boardId;
    private int userSeq;
    private String userName;
    private String userProfileImage;
    private String title;
    private String content;
    private LocalDateTime regDt; // java8 java.util.Date 대체, json 처리 용이
    private int readCount;
    
    private boolean sameUser; // 게시글 상세 (게시글 작성자와 현재 로그인 사용자가 동일인 여부)
    
    public BoardDto() {};
    
    public BoardDto(int userSeq, String title, String content) { // 글 쓰기에서 사용자seq, 제목, 내용만 등록
        this.userSeq = userSeq;
        this.title = title;
        this.content = content;
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
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImage() {
		return userProfileImage;
	}
	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getRegDt() {
        return regDt;
    }
    public void setRegDt(Date regDt) {
        this.regDt = LocalDateTime.ofInstant(
                regDt.toInstant(), ZoneId.systemDefault()
        );  // for Mybatis Date Mapping
    }
    public int getReadCount() {
        return readCount;
    }
    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
    public boolean isSameUser() {
        return sameUser;
    }
    public void setSameUser(boolean sameUser) {
        this.sameUser = sameUser;
    }
    
    @Override
    public String toString() {
        return "BoardDto [boardId=" + boardId + ", userSeq=" + userSeq + ", userName=" + userName
                + ", userProfileImage=" + userProfileImage + ", title=" + title + ", content=" + content
                + ", regDt=" + regDt + ", readCount=" + readCount + ", sameUser=" + sameUser 
                + "]";
    }
}