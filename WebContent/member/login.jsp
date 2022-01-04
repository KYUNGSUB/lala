<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/member/login.css">
<script type="text/javascript" src="../js/login.js"></script>
	<div id="container">
		<section id="outer">
			<div id="leftSec">
				<p><span>라라마켓 회원</span>로그인</p>
				<form action="<c:url value="/login.do" />" method="post">
					<input type="text" id="id" name="id" value="${param.id}" placeholder="아이디를 입력하세요!"><br>
					<input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요!">
					<input type="submit" id="submitBtn" value="로그인">
				</form>
				<p><a href="<c:url value="/join.do" />">회원가입</a> | <a href="<c:url value="#" />">ID/비밀번호 찾기</a></p>
			</div>
			<div id="rightSec">
				<p>고객센터</p>
				<p>070-0000-0000</p>
				<p>평일 10-18시 / 공휴일, 주일 휴무</p>
			</div>
		</section>
	</div>
<%@ include file="../common/footer.jsp" %>