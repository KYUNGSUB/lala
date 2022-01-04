<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/aheader.jsp" %>
<link rel="stylesheet" href="../css/aterms.css">
<script type="text/javascript" src="../js/aterms.js"></script>
<div id="main-wrap">
	<aside>
		<h4>상품 관리</h4>
		<ul>
			<li><a href="1">약관</a></li>
			<li><a href="2">정책</a></li>
			<li><a href="3">결제 방법</a></li>
			<li><a href="4">메뉴 접근 권한</a></li>
		</ul>
	</aside>
	<section id="main-sec">
		<p>| 기본 정책 관리 > 약관 관리</p>
		<h2>| 약관 관리</h2>
		<hr>
		<div>
			<button id="addBtn" class="right">약관 추가하기</button>&nbsp;
			<button id="cancelBtn" class="right">취소</button>&nbsp;
		</div>
		<div id="newTerms" class="listArea">
			<form action="add.do" method="post" name="addFrm">
				<input type="text" size="112" name="title" required placeholder="타이틀을 입력하세요!"><br>
				<textarea rows="10" cols="114" name="content" placeholder="내용을 입력하세요!"></textarea><br>
				<input id="usage" type="radio" name="expose" value="yes"><label for="usage">사용함</label>&nbsp;
				<input id="no_usage" type="radio" name="expose" value="no"><label for="no_usage">사용하지 않음</label>&nbsp;
				<input id="mandatory" type="checkbox" name="mandatory" value="yes"><label for="mandatory">필수 동의</label>
				<p><input type="submit" value="추가"></p>
			</form>
		</div>
		<c:if test="${list.size() == 0}">
			
		</c:if>
	<c:forEach var="term" items="${list}">
		<div class="listArea">
			<form class="modFrm" action="modify.do" method="post" name="modFrm">
				<input type="hidden" name="tid" value="${term.tid}">
				<input type="text" name="title" size="112" value="${term.title}" required><br>
				<textarea rows="10" cols="114" name="content">${term.content}</textarea><br>
			<c:if test="${term.expose == true}">
				<input id="usage" type="radio" name="expose" value="yes" checked="checked"><label for="usage">사용함</label>&nbsp;
				<input id="no_usage" type="radio" name="expose" value="no"><label for="no_usage">사용하지 않음</label>&nbsp;
			</c:if>
			<c:if test="${term.expose == false}">
				<input id="usage" type="radio" name="expose" value="yes"><label for="usage">사용함</label>&nbsp;
				<input id="no_usage" type="radio" name="expose" value="no" checked="checked"><label for="no_usage">사용하지 않음</label>&nbsp;
			</c:if>
			<c:if test="${term.mandatory == true}">
				<input id="mandatory" type="checkbox" name="mandatory" value="yes" checked="checked"><label for="mandatory">필수 동의</label>
			</c:if>
			<c:if test="${term.mandatory == false}">
				<input id="mandatory" type="checkbox" name="mandatory" value="yes"><label for="mandatory">필수 동의</label><br>
			</c:if>
				<p><input type="submit" value="수정" data-oper="modify">&nbsp;<input type="submit" value="삭제" data-oper="remove"></p>
			</form>
		</div>	
	</c:forEach>
	</section>	
</div>
<%@ include file="../common/afooter.jsp" %>