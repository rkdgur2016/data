<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<style>
    .div-1{
        border : 1px solid black;
        width :20%;
    }
    .div-1_info{
       margin : 10%;
    }
    ul{
        list-style: none;
    }
    li{
        padding: 10px;
    }
    .button{
        padding : 10px;
        width: 100px;
        margin : 10px;
    }
</style>
<body>
	<h1>회원가입 양식</h1>
    <hr>
    <div class="div-1">
        <h2 class="div-1_info">양식을 작성해주세요.</h2>
        <form action="/WebProjectTest01/join.do" method="post">
            <ul>
                <li>
                    <label for="user_name"></label>성명<br>
                    <input type="text" id="user_name" name="user_name" maxlength="5">
                </li>
                <li>
                    <label for="user_id"></label>아이디<br>
                    <input type="text" id="user_id" name="user_id" maxlength="12">
                </li>
                <li>
                    <label for="user_pw"></label>비밀번호<br>
                    <input type="password" id="user_pw" name="user_pw" maxlength="12">
                </li>
                <li>
                    <label for="user_phone"></label>전화번호<br>
                    <input type="tel" id="user_phone" name="user_phone" maxlength="12">
                </li>
                <li>
                    <label for="user_email"></label>이메일<br>
                    <input type="email" id="user_email" name="user_email">
                </li>
                <input class="button" type="submit" value="회원가입">
                <input class="button" type="reset" value="양식 초기화">
            </ul>
        </form>
    </div>
</body>
</html>