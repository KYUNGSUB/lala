<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/aheader.jsp" %>
<link rel="stylesheet" href="../css/abanner/agnb.css">
<script type="text/javascript" src="../js/abanner.js"></script>
<div id="main-wrap">
	<aside>
		<h4>배너 관리</h4>
		<ul>
			<li><a href="1">GNB</a></li>
			<li><a href="2">메인</a></li>
			<li><a href="3">스타일 숍 리스트</a></li>
			<li><a href="4">오픈 숍 리스트</a></li>
			<li><a href="5">서브메뉴</a></li>
			<li><a href="6">커뮤니티 리스트</a></li>
			<li><a href="7">고객센터 상단</a></li>
			<li><a href="8">상품 주문완료</a></li>
		</ul>
	</aside>
	<section id="main-sec">
		<p>| 배너관리 > GNB</p>
		<h2>GNB 배너 관리</h2>
		<div id="banner-kind">
			<ul>
				<li><a href="1">상단 배너</a></li>
				<li><a href="2">오른쪽 배너</a></li>
				<li><a href="3">왼쪽 배너</a></li>
				<li><a href="4">하단 배너</a></li>
			</ul>
		</div>
		<div id="expose-method">
			<span>| 노출 방식</span>
			<c:if test="${banner != null}">
				<c:choose>
					<c:when test="${banner.location == 1}">
						<label><input type="radio" name="location" value="slide" checked="checked">슬라이드</label>
						<label><input type="radio" name="location" value="random">랜덤</label>
						<label><input type="radio" name="location" value="login">로그인 전/후</label>
						<label><input type="radio" name="location" value="no">노출하지 않음</label>
					</c:when>
					<c:when test="${banner.location == 2}">
						<label><input type="radio" name="location" value="slide">슬라이드</label>
						<label><input type="radio" name="location" value="random" checked="checked">랜덤</label>
						<label><input type="radio" name="location" value="login">로그인 전/후</label>
						<label><input type="radio" name="location" value="no">노출하지 않음</label>
					</c:when>
					<c:when test="${banner.location == 3}">
						<label><input type="radio" name="location" value="slide">슬라이드</label>
						<label><input type="radio" name="location" value="random">랜덤</label>
						<label><input type="radio" name="location" value="login" checked="checked">로그인 전/후</label>
						<label><input type="radio" name="location" value="no">노출하지 않음</label>
					</c:when>
					<c:when test="${banner.location == 4}">
						<label><input type="radio" name="location" value="slide">슬라이드</label>
						<label><input type="radio" name="location" value="random">랜덤</label>
						<label><input type="radio" name="location" value="login">로그인 전/후</label>
						<label><input type="radio" name="location" value="no" checked="checked">노출하지 않음</label>
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${banner == null}">
				<label><input type="radio" name="location" value="slide">슬라이드</label>
				<label><input type="radio" name="location" value="random">랜덤</label>
				<label><input type="radio" name="location" value="login">로그인 전/후</label>
				<label><input type="radio" name="location" value="no">노출하지 않음</label>
			</c:if>
		</div>
		<div id="banner-add">
			<span>| 등록된 배너</span><button id="addBtn">배너 추가</button>
		</div>
		<div id="banner-area">
			<div id="new-banner">
				<form action="#" method="post" enctype="multipart/form-data">
					<input type="hidden" id="kind" name="kind" value="${param.kind}">
					<input type="hidden" id="position" name="position" value="${param.position}">
					<table>
						<tr>
							<td class="first"><input id="infoCheck" type="checkbox" name="infoCheck" value="new"></td>
							<td colspan="2"><span>등록된 배너가 없습니다.</span></td>
						</tr>
						<tr>
							<td class="first">*배너 이미지</td>
							<td colspan="2"><input type="file" name="bannerImg"></td>
						</tr>
						<tr>
							<td class="first">링크 주소</td>
							<td><input type="text" size="60" name="url">
							<td>
								<label><input type="radio" name="target" value="blank">새 창(blank)</label>
								<label><input type="radio" name="target" value="self">본 창(self)</label>
							</td>
						</tr>
						<tr>
							<td class="first">대체 텍스트</td>
							<td><input type="text" size="60" name="alt"></td>
							<td>
								<select name="login">
									<option value="default"></option>
									<option value="before">로그인 전</option>
									<option value="after">로그인 후</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<hr>
	<c:if test="${banner != null}">
		<c:forEach var="info" items="${banner.infoList}" varStatus="status">
			<div class="infoClass ic${status.index}">
				<form action="#" method="post" enctype="multipart/form-data">
					<input type="hidden" id="kind" name="kind" value="${param.kind}">
					<input type="hidden" id="position" name="position" value="${param.position}">
					<input type="hidden" id="bid" name="bid" value="${info.bid}">
					<input type="hidden" id="info_id" name="info_id" value="${info.info_id}">
					<table>
						<tr>
							<td class="first"><input id="infoCheck" type="checkbox" name="infoCheck" value="old${status.index}"></td>
							<td colspan="2">
								<c:set var="pds" value="${info.pds}"></c:set>
								<c:url value="/pds/download.do" var="displayUrl">
									<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
								</c:url>
								<img src="<c:url value="${displayUrl}" />" alt="Banner Image" style="width: 700px; height: 100px;">
							</td>
						</tr>
						<tr>
							<td class="first">*배너 이미지</td>
							<td colspan="2"><input type="file" name="bannerImg"></td>
						</tr>
						<tr>
							<td class="first">링크 주소</td>
							<td><input type="text" size="60" name="url" value="${info.url}"><td>
						<c:choose>
							<c:when test="${info.target == 'blank'}">
								<label><input type="radio" name="target" value="blank" checked="checked">새 창(blank)</label>
								<label><input type="radio" name="target" value="self">본 창(self)</label>		
							</c:when>
							<c:otherwise>
								<label><input type="radio" name="target" value="blank">새 창(blank)</label>
								<label><input type="radio" name="target" value="self" checked="checked">본 창(self)</label>		
							</c:otherwise>
						</c:choose>
							</td>
						</tr>
						<tr>
							<td class="first">대체 텍스트</td>
							<td><input type="text" size="60" name="alt" value="${info.alt}"></td>
							<td>
								<select name="login">
							<c:choose>
								<c:when test="${info.loginBefore == 0}">
									<option value="default" selected="selected"></option>
									<option value="before">로그인 전</option>
									<option value="after">로그인 후</option>
								</c:when>
								<c:when test="${info.loginBefore == 1}">
									<option value="default"></option>
									<option value="before" selected="selected">로그인 전</option>
									<option value="after">로그인 후</option>
								</c:when>
								<c:otherwise>
									<option value="default"></option>
									<option value="before">로그인 전</option>
									<option value="after" selected="selected">로그인 후</option>
								</c:otherwise>
							</c:choose>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>	
		</c:forEach>
	</c:if>
		</div>
		<div id="banner-btn">
			<button type="submit" data-oper="remove">선택 삭제</button>
			<button type="submit" data-oper="modify">수정하기</button>
		</div>
	</section>	
</div>
<%@ include file="../common/afooter.jsp" %>