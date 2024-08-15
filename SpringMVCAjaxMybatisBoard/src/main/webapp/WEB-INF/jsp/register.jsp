<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="container">
		<div class="mb-3 mt-3 d-flex justify-content-center">
            <h1 class="display-4">민수의 회원가입 놀이</h1>
        </div>
        <div class="mb-3">
            <h2>Register</h2>
        </div>
        
         <form novalidate>
            <div class="mb-3">
                <label for="userName">User Name:</label>
                <input type="text" class="form-control" id="userName" placeholder="Enter userName" name="userName" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="mb-3">
                <label for="userPassword">Password:</label>
                <input type="password" class="form-control" id="userPassword" placeholder="Enter password" name="userPassword" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.</div>
            </div>
            <div class="mb-3">
                <label for="userPassword2">Password Confirm:</label>
                <input type="password" class="form-control" id="userPassword2" placeholder="Confirm password" name="userPassword2" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="mb-3">
                <label for="userEmail">Email:</label>
                <input type="text" class="form-control" id="userEmail" placeholder="Enter email" name="userEmail" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
        </form>
         <div>
            <button id="btnRegister" class="btn btn-primary">가입</button>
        </div>
	</div>
<script>
window.onload = function(){
    
    document.querySelector("#userName").focus();
    document.querySelector("#userName").onblur = function () {
        if (validateUserName( this.value )) {
            this.classList.remove("is-invalid");
            this.classList.add('is-valid');
        } else {
            this.classList.remove("is-valid");
            this.classList.add('is-invalid');
        }
    };
    document.querySelector("#userPassword").onblur = function () {
        if (validatePassword( this.value )) {
            this.classList.remove("is-invalid");
            this.classList.add('is-valid');
        } else {
            this.classList.remove("is-valid");
            this.classList.add('is-invalid');
        }
    };
    document.querySelector("#userPassword2").onblur = function () {
        if (validatePassword2( this.value )) {
            this.classList.remove("is-invalid");
            this.classList.add('is-valid');
        } else {
            this.classList.remove("is-valid");
            this.classList.add('is-invalid');
        }
    };
    document.querySelector("#userEmail").onblur = function () {
        if (validateEmail( this.value )) {
            this.classList.remove("is-invalid");
            this.classList.add('is-valid');
        } else {
            this.classList.remove("is-valid");
            this.classList.add('is-invalid');
        }
    };
    document.querySelector('input').onfocus = function () {
        this.classList.remove('is-valid', 'is-invalid');
    };
    
    //submit
    document.querySelector("#btnRegister").onclick = function(){
        if( document.querySelectorAll("form .is-invalid").length > 0 ){
            alert("입력이 올바르지 않습니다.")
        }else{
            register();
        }
    };
}
function validateUserName(userName) {
    if (userName.length >= 3) return true;
    else return false;
}
function validatePassword(userPassword) {
    var patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// + for at least one
    var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// + for at least one
    var patternNumAtListOne = new RegExp(/[0-9]+/);// + for at least one
    
    if( patternEngAtListOne.test( userPassword ) 
            && patternSpeAtListOne.test( userPassword )  
            && patternNumAtListOne.test( userPassword )
            && userPassword.length >= 8
    ){
        return true;
    }
    else return false;
}
function validatePassword2(userPassword2) {
    if (userPassword2 == document.querySelector("#userPassword").value ) return true;
    else return false;
}
function validateEmail(userEmail) {
    // ^ 시작일치, $ 끝 일치
    // {2, 3} 2개 ~ 3개
    // * 0회 이상, + 1회 이상
    // [-_.] - 또는 _ 또는 .
    // ? 없거나 1회
    let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regexp.test(userEmail)) return true;
    else return false;
}
async function register() {
    
    var userName = document.querySelector("#userName").value;
    var userPassword = document.querySelector("#userPassword").value; 
    var userEmail = document.querySelector("#userEmail").value;   
    
    let urlParams = new URLSearchParams({
        userName: userName,
        userEmail: userEmail,
        userPassword: userPassword,
    });
    
    let fetchOptions = {
        method: "POST",
        body: urlParams,
    }
    
    let response = await fetch("/register", fetchOptions);
    console.log(response);
    let data = await response.json();
    if( data.result == "success" ){
        alertify.alert('Welcome!', '회원가입을 축하합니다. 로그인 페이지로 이동합니다.', function(){
            window.location.href="/pages/login";
        });
    }else if( data.result == "fail" ){
        alert("서버 오류!!");
    }
}
</script>
</body>
</html>