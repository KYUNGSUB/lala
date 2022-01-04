<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/member/terms.css">
<script type="text/javascript" src="../js/terms.js"></script>
	<div id="container">
		<aside>
			<img src="../images/term_aside1.png" alt="회원가입 절차">
		</aside>
		<section>
			<h4>| 라라마켓 서비스 약관 동의</h4>
	<c:forEach var="term" items="${list}"><%-- 각 약관 별로 --%>
		<c:if test="${term.expose}"><%-- 노출일 경우 --%>
			<article title="${term.title}" data-oper="${term.tid}"><!-- 약관 보여주기 -->
				<div class="service-wrap">
					<p>${term.title}</p>
					<div class="content-box">
						${term.content}
					</div>
					<label>
						<input type="checkbox" name="agreement" data-oper="${term.mandatory}">
					<c:if test="${term.mandatory == true}"><%-- 필수/선택 여부 --%>
						동의함(필수)
					</c:if>
					<c:if test="${term.mandatory == false}">
						동의함(선택)
					</c:if>	
					</label>
				</div>
			</article>
		</c:if>
	</c:forEach>
			<article id="allagree-check">
				<input type="checkbox" id="all-agree" value="all-agree">
				<label for="all-agree">모두 동의</label><span class="margin1"></span>
				<form id="agreeForm" action="<c:url value="/terms.do"/>" method="post">
					<input class="btnA" type="submit" value="다음"><span class="margin2"></span>
					<input class="btnA" type="button" value="취소">
				</form>
			</article>
		</section>
	</div>
<%@ include file="../common/footer.jsp" %>