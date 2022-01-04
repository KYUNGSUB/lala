<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.aid == null}">
<c:redirect url="/alogin.do"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>라라마켓 관리자 페이지</title>
<link rel="stylesheet" href="<c:url value="/css/common/reset.css" />">
<link rel="stylesheet" href="<c:url value="/css/common/acommon.css" />">
<script src="<c:url value="/js/jquery.js" />"></script>
<script src="<c:url value="/js/acommon.js" />"></script>
</head>
<body>
	<div id="wrap">
		<header id="header">
			<div id="logo-wrap">
				<a href="aindex.do">라라마켓</a>
				<span>Admin</span>
			</div>
			<div id="subMenu">
				<button onclick="#">이용자 화면 보기</button>
				<button onclick="#">사내 게시판</button>
				<button onclick="location.href='/alogout.do'">로그아웃</button>
			</div>
			<nav id="gnb">
				<ul>
					<li>
						<a href="#">주문 관리</a>
						<ul class="m1">
							<li><a href="#">스타일 숍 주문</a></li>
							<li><a href="#">오픈 숍 주문</a></li>
							<li><a href="#">주문 취소</a></li>
							<li><a href="#">교환/환불 신청</a></li>
						</ul>
					</li>
					<li>
						<a href="#">게시판 관리</a>
						<ul class="m2">
							<li><a href="#">공지사항</a></li>
							<li><a href="#">커뮤니티</a></li>
							<li><a href="#">상품 문의</a></li>
							<li><a href="#">1:1 문의</a></li>
							<li><a href="#">상품평</a></li>
							<li><a href="#">자주 하는 질문</a></li>
							<li><a href="#">이벤트</a></li>
							<li><a href="#">허위 상품 접수</a></li>
							<li><a href="#">게시판 카테고리 관리</a></li>
						</ul>
					</li>
					<li>
						<a href="#">회원 관리</a>
						<ul class="m3">
							<li><a href="#">회원 리스트</a></li>
							<li><a href="#">탈퇴 회원 리스트</a></li>
							<li><a href="#">메일 관리</a></li>
							<li><a href="#">SMS 관리</a></li>
							<li><a href="#">대량 메일 발송</a></li>
						</ul>
					</li>
					<li>
						<a href="#">메인 관리</a>
						<ul class="m4">
							<li><a href="#">1열 상품</a></li>
							<li><a href="#">2열 상품</a></li>
							<li><a href="#">카테고리별 상품</a></li>
						</ul>
					</li>
					<li>
						<a href="#">배너 관리</a>
						<ul class="m5">
							<li><a href="<c:url value="/abanner/gnb.do" />">GNB</a></li>
							<li><a href="#">메인</a></li>
							<li><a href="#">스타일 숍 리스트</a></li>
							<li><a href="#">오픈 숍 리스트</a></li>
							<li><a href="#">서브 메뉴</a></li>
							<li><a href="#">커뮤니티 리스트</a></li>
							<li><a href="#">고객센터 상단</a></li>
							<li><a href="#">상품 주문완료</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value="/product/register.do" />">상품 관리</a>
						<ul class="m6">
							<li><a href="<c:url value="/product/register.do" />">상품 등록</a></li>
							<li><a href="<c:url value="/product/list.do" />">상품 리스트</a></li>
							<li><a href="<c:url value="/category/show.do" />">상품 카테고리 관리</a></li>
						</ul>
					</li>
					<li>
						<a href="#">통계</a>
						<ul class="m7">
							<li><a href="#">방문자수/페이지뷰</a></li>
							<li><a href="#">회원수</a></li>
							<li><a href="#">상품 조회수</a></li>
							<li><a href="#">카테고리 조회수</a></li>
							<li><a href="#">상품 판매 순위</a></li>
							<li><a href="#">포인트</a></li>
							<li><a href="#">탙퇴 사유</a></li>
						</ul>
					</li>
					<li>
						<a href="<c:url value="/terms/list.do" />">정책 관리</a>
						<ul class="m8">
							<li><a href="<c:url value="/terms/list.do" />">약관</a></li>
							<li><a href="<c:url value="/policy/manage.do" />">정책</a></li>
							<li><a href="#">결재 방법</a></li>
							<li><a href="#">메뉴 접근 권한</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</header>