<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>라라마켓</title>
<link rel="stylesheet" href="css/common/common.css">
<script src="js/jquery.js"></script>
<script src="js/common.js"></script>
</head>
<body>
	<div id="header-wrap">
		<header class="header-inner">
			<div class="top-banner-wrap">
				<h1 class="banner-inner">banner</h1>
			</div>
			<h1 class="logo">
				<a href="<c:url value="/"/>">라라마켓</a>
			</h1>
			<c:if test="${sessionScope.id == null}">
				<h4 class="marketing-msg">안입는 옷을 판매하고, 새 옷으로 구매하세요!</h4>
			</c:if>
			<c:if test="${sessionScope.id != null}">
				<h4 class="marketing-msg">입지 않는 옷! 오픈 숍에서 판매하고 포인트를 적립하세요!</h4>
			</c:if>
			<div id="search_section">
				<form id="searchForm" action="searchRequest.do" method="get">
					<select name="searchType">
						<option value="all">전체 검색</option>
						<option value="style">스타일 샵 상품</option>
						<option value="open">오픈 숍 상품</option>
						<option value="notice">공지사항</option>
						<option value="bulletin">게시판 제목/내용</option>
						<option value="community">커뮤니티</option>
						<option value="product">상품 문의 내용</option>
					</select>
					<input type="text" size="40" placeholder="검색어를 입력하세요!" name="keyword">
					<button>검색</button>
				</form>
			</div>
			<c:if test="${sessionScope.id == null}">
			<ul class="subMenu">
				<li><a href="<c:url value="/terms.do" />">회원가입</a></li>
				<li><a href="<c:url value="/login.do" />">로그인</a></li>
				<li><a href="#">ID/비밀번호 찾기</a></li>
				<li><a href="#">비회원 주문조회</a></li>
			</ul>
			</c:if>
			<c:if test="${sessionScope.id != null}">
			<ul class="subMenu">
				<li><a href="#">장바구니</a></li>
				<li><a href="#">주문내역</a></li>
				<li><a href="#">1:1문의</a></li>
				<li><a href="#">마이 페이지</a></li>
				<li><a href="<c:url value="/logout.do" />">로그아웃</a></li>
			</ul>
			</c:if>
			<p class="mobile-menu-open">
				<button>
					<span class="blind">메뉴 열기</span>
					<span></span>
					<span></span>
					<span></span>
				</button>
			</p>
			<div class="mobile-menu-wrap">
				<div class="mobile-menu-scroll">
					<nav id="gnb">
						<h2 class="blind">메인메뉴</h2>
						<ul>
							<li class="m1">
								<a href="#">오픈 숍</a>
								<div class="secMenu secMenu1">
									<ul>
										<li><a href="#">Outer</a></li>
										<li><a href="#">Top</a></li>
										<li><a href="#">Bottom</a></li>
										<li><a href="#">Skirt</a></li>
										<li><a href="#">Dress</a></li>
									</ul>
									<div class="proMenu">
										<h3>최근 등록된 상품</h3>
										<div class="proOuter">
											<div class="proBox"></div>
											<div class="proBox"></div>
											<div class="proBox"></div>
											<div class="proBox"></div>
										</div>
									</div>
								</div>
							</li>
							<li class="m2">
								<a href="#">스타일 숍</a>
								<div class="secMenu secMenu1">
									<ul>
										<li><a href="#">Outer</a></li>
										<li><a href="#">Top</a></li>
										<li><a href="#">Bottom</a></li>
										<li><a href="#">Skirt</a></li>
										<li><a href="#">Dress</a></li>
									</ul>
									<div class="proMenu">
										<h3>관리자 지정 상품</h3>
										<div class="proOuter">
											<div class="proBox"></div>
											<div class="proBox"></div>
											<div class="proBox"></div>
											<div class="proBox"></div>
										</div>
									</div>
								</div>
							</li>
							<li class="m3">
								<a href="#">라라마켓</a>
								<ul class="secMenu secMenu2">
									<li><a href="#">라라마켓 소개</a></li>
									<li><a href="#">대표인사말</a></li>
									<li><a href="#">찾아오시는 길</a></li>
								</ul>
							</li>
							<li class="m4 no-sub">
								<a href="#">상품평</a>
							</li>
							<li class="m5 no-sub">
								<a href="#">이벤트</a>
							</li>
							<li class="m6">
								<a href="#">고객센터</a>
								<ul class="secMenu secMenu3">
									<li><a href="#">공지사항</a></li>
									<li><a href="#">커뮤니티</a></li>
									<li><a href="#">자주하는 질문</a></li>
									<li><a href="#">상품문의</a></li>
									<li><a href="#">아이디 찾기</a></li>
									<li><a href="#">비밀번호 찾기</a></li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<p class="mobile-menu-close">
				<button>
					<span class="blind">메뉴닫기</span>
					<span></span>
					<span></span>
				</button>
			</p>
		</header>
	</div>