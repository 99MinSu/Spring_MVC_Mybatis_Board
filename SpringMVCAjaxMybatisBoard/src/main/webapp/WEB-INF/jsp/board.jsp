<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mycom.myapp.user.dto.UserDto" %>
<%
    // Back Forward Cache (BFCache )
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
    System.out.println(userDto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container">
    <a class="navbar-brand" href="#"><img alt="" src="/assets/img/user/<%=userDto.getUserProfileImage()%>" style="width:24px; height: 24px; border-radius: 50%;"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="/pages/board">게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>        
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="/auth/logout">Logout</a>
        </li>        
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-3">
    <h4 class="text-center">게시판</h4>       
  
    <div class="input-group mb-3">
      <input id="inputSearchWord" type="text" class="form-control" placeholder="Search">
      <button id="btnSearchWord" class="btn btn-success" type="button">Search</button>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody id="boardTbody">
        </tbody>
    </table>
   
    <div id="paginationWrapper"></div>
    <button class="btn btn-sm btn-primary" id="btnInsertPage">글쓰기</button>
</div>
<!-- insert modal -->
<div class="modal" tabindex="-1" id="insertBoardModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">글 쓰기</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
        <div class="mb-3">
          <label for="titleInsert" class="form-label">제목</label>
          <input type="text" class="form-control" id="titleInsert">
        </div>
        <div class="mb-3">
          <label for="contentInsert" class="form-label">내용</label>
          <textarea class="form-control" id="contentInsert" rows="10"></textarea>
        </div>
        <button id="btnBoardInsert" class="btn btn-sm btn-primary btn-outline" data-bs-dismiss="modal" type="button">등록</button>
        
      </div>
    </div>
  </div>
</div>
<!-- detail modal -->
<div class="modal" tabindex="-1" id="detailBoardModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">글 상세</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
        <div class="example table-responsive">
            <table class="table">
              <tbody>
                <tr><td>글번호</td><td id="boardIdDetail">#</td></tr>
                <tr><td>제목</td><td id="titleDetail">#</td></tr>
                <tr><td>내용</td><td id="contentDetail">#</td></tr>
                <tr><td>작성자</td><td id="userNameDetail">#</td></tr>
                <tr><td>작성일시</td><td id="regDtDetail">#</td></tr>
                <tr><td>조회수</td><td id="readCountDetail">#</td></tr>
              </tbody>
            </table>
        </div>        
        <button id="btnBoardUpdateForm" class="btn btn-sm btn-primary btn-outline" data-bs-dismiss="modal" type="button">글 수정하기</button>
        <button id="btnBoardDeleteConfirm" class="btn btn-sm btn-warning btn-outline" data-bs-dismiss="modal" type="button">글 삭제하기</button>
        
      </div>
    </div>
  </div>
</div>
<!-- update modal -->
<div class="modal" tabindex="-1" id="updateBoardModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">글 수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        
        <div class="mb-3">
          <label for="titleUpdate" class="form-label">제목</label>
          <input type="text" class="form-control" id="titleUpdate">
        </div>
        <div class="mb-3">
          <label for="contentUpdate" class="form-label">내용</label>
          <textarea class="form-control" id="contentUpdate" rows="10"></textarea>
        </div>
        <button id="btnBoardUpdate" class="btn btn-sm btn-primary btn-outline" data-bs-dismiss="modal" type="button">수정</button>
        
      </div>
    </div>
  </div>
</div>
<script src="/assets/js/util.js"></script>
<script>
    let LIST_ROW_COUNT = 10;
    let OFFSET = 0;
    let SEARCH_WORD = '';
    
    window.onload = function(){
        
        // 글 목록
        listBoard();
        
        // 글 목록 검색어 포함
        document.querySelector("#btnSearchWord").onclick = function(){
            SEARCH_WORD = document.querySelector("#inputSearchWord").value;
            
            listBoard();
        }
        
        // 글 등록 모달
        document.querySelector("#btnInsertPage").onclick = function(){
            document.querySelector("#titleInsert").value = '';
            document.querySelector("#contentInsert").value = '';
            
            // bootstrap 5 model show
            let modal = new bootstrap.Modal( document.querySelector("#insertBoardModal"), { keyboard: false } );
            modal.show();
        }
        
        // 글 등록
        document.querySelector("#btnBoardInsert").onclick = function(){
            if( document.querySelector("#titleInsert").value.length > 0 && document.querySelector("#contentInsert").value.length > 0 ){
                insertBoard();
            }
        }
        
        // 글 수정 모달
        document.querySelector("#btnBoardUpdateForm").onclick = function(){
            // 수정 글 boardId 
            let boardId = document.querySelector("#detailBoardModal").getAttribute("data-boardId");
            document.querySelector("#updateBoardModal").setAttribute("data-boardId", boardId);
            
            // 글 제목, 내용은 detailModal 로부터
            document.querySelector("#titleUpdate").value = document.querySelector("#titleDetail").innerHTML;
            document.querySelector("#contentUpdate").value = document.querySelector("#contentDetail").innerHTML;
            
            let modal = new bootstrap.Modal( document.querySelector("#updateBoardModal"), { keyboard: false } );
            modal.show();
        }
        
        // 글 수정
        document.querySelector("#btnBoardUpdate").onclick = function(){
            if( document.querySelector("#titleUpdate").value.length > 0 && document.querySelector("#contentUpdate").value.length > 0 ){
                updateBoard();
            }
        }
        
        // 글 삭제
        document.querySelector("#btnBoardDeleteConfirm").onclick = function(){
            alertify.confirm('삭제 확인', '이 글을 삭제하시겠습니까?', 
            function(){
                deleteBoard();
            }, 
            function(){
                console.log("삭제 취소");
            });
        }
    }
    async function listBoard() {
        let url = "/boards/list"
        let urlParams = "?limit=" + LIST_ROW_COUNT + "&offset=" + OFFSET + "&searchWord=" + SEARCH_WORD;
        let response = await fetch(url + urlParams);
        let data = await response.json();
        
        if( data.result == "success" ){ // 게시판 페이지 이동
            makeListHtml(data.list);
            console.log(data.count);
        }else if( data.result == "fail" ){
            alert("글 조회과정에서 오류가 발생했습니다.");
        }
    }
    
    function makeListHtml(list){
        let listHTML = ``;
        
        list.forEach( el => {
            let boardId = el.boardId;
            let userName = el.userName;
            let title = el.title;
            let content = el.content;
            let regDt = el.regDt;
            // LocalDateTime 객체 --> json 처리 결과물이 gson, jackson 2가지가 다르다.
//          console.log(regDt);
            let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.'); // 2024.07.11
            let readCount = el.readCount;
            
            listHTML += `<tr style="cursor:pointer" data-boardId=\${boardId}>
                            <td>\${boardId}</td>
                            <td>\${title}</td>
                            <td>\${userName}</td>
                            <td>\${regDtStr}</td>
                            <td>\${readCount}</td>
                        </tr>`;
        });     
        
        document.querySelector("#boardTbody").innerHTML = listHTML;
        
        document.querySelectorAll("#boardTbody tr").forEach( el => {
            el.onclick = function(){
                let boardId = this.getAttribute("data-boardId");
                detailBoard(boardId);
            }
        } );
    }
    
    async function detailBoard(boardId) {
        let url = "/boards/detail/" + boardId
        let response = await fetch(url);
        let data = await response.json();
        
        if( data.result == "success" ){ // 게시판 페이지 이동
            makeDetailHtml(data.dto);
        
        }else if( data.result == "fail" ){
            alert("글 상세 조회 과정에서 오류가 발생했습니다.");
        }
    }
    
    function makeDetailHtml(detail){
        let boardId = detail.boardId;
        let userSeq = detail.userSeq;
        let userName = detail.userName;
        let title = detail.title;
        let content = detail.content;
        let regDt = detail.regDt;
        let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') + ' ' + makeTimeStr(regDt.time.hour, regDt.time.minute, regDt.time.second, ':'); // for Gson Format Of LocalDateTime
        let readCount = detail.readCount;
        let sameUser = detail.sameUser;
                
        document.querySelector("#detailBoardModal").setAttribute("data-boardId", boardId);
        document.querySelector("#boardIdDetail").innerHTML = "#" + boardId;
        document.querySelector("#titleDetail").innerHTML = title;
        document.querySelector("#contentDetail").innerHTML = content;
        document.querySelector("#userNameDetail").innerHTML = userName;
        document.querySelector("#regDtDetail").innerHTML = regDtStr;
        document.querySelector("#readCountDetail").innerHTML = readCount;
        if( sameUser ){
            document.querySelector("#btnBoardUpdateForm").style.display = "inline-block";
            document.querySelector("#btnBoardDeleteConfirm").style.display = "inline-block";
        }else{
            document.querySelector("#btnBoardUpdateForm").style.display = "none";
            document.querySelector("#btnBoardDeleteConfirm").style.display = "none";
        }   
        
        // bootstrap 5 model show
        let modal = new bootstrap.Modal( document.querySelector("#detailBoardModal"), { keyboard: false } );
        modal.show();
    } 
    
    
    async function insertBoard() {
        let urlParams = new URLSearchParams({
            title: document.querySelector("#titleInsert").value,
            content: document.querySelector("#contentInsert").value
        });
        
        let url = "/boards/insert";
        
        let fetchOptions = {
            method: 'POST',
            body: urlParams
        }
        let response = await fetch(url, fetchOptions);
        let data = await response.json();
        
        if( data.result == "success" ){ // 게시판 페이지 이동
            alertify.success('글이 등록되었습니다.');
            listBoard();
        }else if( data.result == "fail" ){
            alert("글 등록 과정에서 오류가 발생했습니다.");
        }
    }
    
    async function updateBoard() {
        let boardId = document.querySelector("#updateBoardModal").getAttribute("data-boardId");
        
        let urlParams = new URLSearchParams({
            boardId: boardId,
            title: document.querySelector("#titleUpdate").value,
            content: document.querySelector("#contentUpdate").value
        });
        
        let url = "/boards/update";
        
        let fetchOptions = {
            method: 'POST',
            body: urlParams
        }
        let response = await fetch(url, fetchOptions);
        let data = await response.json();
        
        if( data.result == "success" ){ // 게시판 페이지 이동
            alertify.success('글이 수정되었습니다.');
            listBoard();
        }else if( data.result == "fail" ){
            alert("글 수정 과정에서 오류가 발생했습니다.");
        }
    }
    
    async function deleteBoard() {
        let boardId = document.querySelector("#detailBoardModal").getAttribute("data-boardId");
        let url = "/boards/delete/" + boardId;
        let response = await fetch(url);
        let data = await response.json();
        
        if( data.result == "success" ){ // 게시판 페이지 이동
            alertify.success('글이 삭제되었습니다.');
            listBoard();
        }else if( data.result == "fail" ){
            alert("글 삭제 과정에서 오류가 발생했습니다.");
        }
    }
</script>
</body>
</html>