<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/aheader.jsp" %>
<link rel="stylesheet" href="../css/product/list.css">
<script type="text/javascript" src="../js/product/list.js"></script>
<div id="main-wrap">
	<aside>
		<h4>상품 관리</h4>
		<ul>
			<li><a href="1">상품 등록</a></li>
			<li><a href="2">상품 리스트</a></li>
			<li><a href="3">카테고리 관리</a></li>
		</ul>
	</aside>
	<section id="main-sec">
		<c:set var="paging" value="${productModel.paging}"/>
		<c:set var="list" value="${productModel.productList}" />
		<p class="p1">| 상품 관리 > 상품 리스트 > 스타일 숍</p>
		<h2>| 스타일 숍 상품 리스트</h2>
		<form action="list.do" method="get" name="search">
			<input type="hidden" name="pageNum" value="${paging.cri.pageNum}">
			<input type="hidden" name="amount" value="${paging.cri.amount}">
		<div id="searchArea">
			<div>
				<select name="type">
			<c:choose>
				<c:when test="${empty paging.cri.type}">
					<option value="none" selected="selected">-</option>
					<option value="name">상품명</option>
					<option value="regid">등록ID</option>
				</c:when>
				<c:when test="${paging.cri.type == 'name'}">
					<option value="none">-</option>
					<option value="name" selected="selected">상품명</option>
					<option value="regid">등록ID</option>
				</c:when>
				<c:otherwise>
					<option value="none">-</option>
					<option value="name">상품명</option>
					<option value="regid" selected="selected">등록ID</option>
				</c:otherwise>
			</c:choose>
				</select>&nbsp;
				<input type="text" name="keyword" value="${paging.cri.keyword}" placeholder="검색어를 입력하세요">
			</div>
			<div>
				<a id="showBtn" href="#">상세 검색</a> &nbsp; <span><a id="closeBtn" href="#">닫기</a></span>
			</div>
			<hr>
			<table>
				<tr>
					<td>
						카테고리 선택
						<select name="category1">
							<option value="" data-oper="">1차 선택</option>
						</select>
						<select name="category2">
							<option value="" data-oper="">2차 선택</option>
						</select>
					</td>
					<td>
						판매 가격&nbsp;<input type="text" size="10" name="priceFrom">원 ~ 
						<input type="text" size="10" name="priceTo">원
					</td>
				</tr>
				<tr>
					<td>
						상품 등록일 <input type="date" name="regFrom">&nbsp; ~ &nbsp;<input type="date" name="regTo">
					</td>
					<td>
						진열 여부 &nbsp; 
						<label><input type="checkbox" name="expose" value="진열">진열</label>&nbsp;
						<label><input type="checkbox" name="expose" value="품절">품절</label>&nbsp;
						<label><input type="checkbox" name="expose" value="숨김">숨김</label>
					</td>
				</tr>
			</table>
			<p><input type="submit" value="검색"></p>
		</div>
		</form>
		<div id="pArea">
		<c:if test="${empty list}">
			<div>
				<p>등록된 상품이 없습니다.</p>
			</div>
		</c:if>
		<c:if test="${!empty list}">
			<div>
				<h4>총 등록 상품 : ${paging.totalCount}개 / 검색된 상품 : ${paging.numberOfRecords}개</h4>
			</div>
			<div>
				<table>
					<tr>
						<th>선택</th>
						<th>번호</th>
						<th>카테고리와 상품명</th>
						<th>판매 가격<br>(적립금)</th>
						<th>상태</th>
						<th>등록일<br>(수정일)</th>
						<th>아이디</th>
						<th>조회수</th>
						<th>수정</th>
					</tr>
				<c:forEach var="product" items="${list}">
					<tr>
						<td><input type="checkbox" class="boxBtn"></td>
						<td>${product.pid}</td>
						<td>[${product.category2}]<br><a href="#">${product.name}</a></td>
						<td>${product.salePrice}<br>(${product.deposit})</td>
						<td>${product.expose}</td>
						<td>
							<fmt:formatDate value="${product.createdAt}" type="date"></fmt:formatDate><br>
							(<fmt:formatDate value="${product.modifiedAt}" type="date"></fmt:formatDate>)
						</td>
						<td>${product.userid}</td>
						<td>${product.readCount}</td>
						<td><input type="button" value="수정" onclick="location.href='/product/update.do?pid=${product.pid}'"></td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<div><input type="button" name="remove" value="삭제">&nbsp;
				<input type="button" name="show" value="진열">&nbsp;
				<input type="button" name="hide" value="숨김">&nbsp;
				<input type="button" name="out" value="품절">
			</div>
			<div>
				<p id="pagingArea">
					<a href="${paging.firstPageNo}">[처음]</a>&nbsp;
				<c:if test="${paging.prevPageNo < pagin.startPageNo}">
					<a href="${paging.prevPageNo}">&lt;이전</a>&nbsp;|
				</c:if>
			<c:forEach begin="${paging.startPageNo}" end="${paging.endPageNo}" var="p">
				<c:if test="${p == paging.currentPageNo}">
					<a class="on" href="${p}">[${p}]</a>
				</c:if>
				<c:if test="${p != paging.currentPageNo}">
					<a href="${p}">[${p}]</a>
				</c:if>
			</c:forEach>
				<c:if test="${paging.nextPageNo > paging.endPageNo}">
					| <a href="${paging.nextPageNo}">다음&gt;</a>
				</c:if>
					&nbsp;<a href="${paging.finalPageNo}">[마지막]</a>
				</p>
			</div>
		</c:if>
		</div>
	</section>
</div>
<%@ include file="../common/afooter.jsp" %>