<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
	<h2>축하드립니다! 회원가입에 성공하셨습니다</h2>
	<hr>
    	<p>성명: <%= request.getAttribute("user_name") %></p>
    	<p>아이디: <%= request.getAttribute("user_id") %></p>
    	<p>비밀번호: <%= request.getAttribute("user_pw") %></p>
    	<p>전화번호: <%= request.getAttribute("user_phone") %></p>
    	<p>이메일:<%= request.getAttribute("user_email") %></p>
</body>
</html>