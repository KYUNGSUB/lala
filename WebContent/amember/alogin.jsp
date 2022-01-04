<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라라마켓 관리자 로그인</title>
<link rel="stylesheet" href="css/common/reset.css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<style>
#wrap {
	max-width: 1200px;
	width: 90%;
	margin: 0 auto;
}
#t_header {
	margin-top: 10px;
	padding: 10px;
	border-bottom: 1px solid black;
	font-weight: bold;
}
#t_header h3 {
	font-size: 1.2em;
	font-size: 1.2rem;
}
#login_w {
	position: relative;
	width: 260px;
	margin: 0 auto;
	margin-top: 100px;
}
#login_w h4 {
	font-weight: bold;
	margin-bottom: 15px;
}
#login_w input {
	padding: 5px;
	margin-bottom: 10px;
}
#login_w button {
	position: absolute;
	right: 0;
	top: 32px;
	padding: 15px;
}
#guide_w {
	width: 500px;
	margin: 0 auto;
	margin-top: 30px;
	line-height: 1.5;
}
table {
	width: 100%;
	border: 1px solid black;
	border-collapse: collapse;
}
th, td {
	border: 1px solid black;
	font-size: 0.8em;
	font-size: 0.8rem;
}
th {
	background-color: #ddd;
	padding: 5px;
	font-weight: bold;
}
td {
	padding: 5px 5px 10px;
}
</style>
</head>
<body>
	<div id="wrap">
		<div id="t_header">
			<h3>| 라라마켓 Admin Login</h3>
		</div>
		<div id="login_w">
			<h4>| Admin Login</h4>
			<form action="<c:url value="/alogin.do" />" method="post">
				<input type="text" id="id" name="id" placeholder="아이디를 입력하세요!"><br>
				<input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력해 주세요!">
				<button type="submit">보안<br>로그인</button>
			</form>
		</div>
		<div id="guide_w">
			<table>
				<tr>
					<th>관리자 계정 생성 안내</td>
				</tr>
				<tr>
					<td>
					- 이용자 화면에서 회원 가입한 후 아래의 정보로 연락해 주세요!<br>
					- 홍길동과장 / 000-0000-0000 / admin@style.co.kr<br>
					- 아이디와 비밀번호는 이용자 화면에서 찾을 수 있습니다
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>