<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/aheader.jsp" %>
<link rel="stylesheet" href="../css/apolicy.css">
<script type="text/javascript" src="../smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="../js/apolicy.js"></script>
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
		<p class="p1">| 기본 정책 관리 > 정책 관리</p>
		<h2>| 정책 관리</h2>
		<hr>
	<form action="manage.do" method="post">
		<h3>| 배송 정책</h3>
	<c:if test="${empty list}">
		<p class="p2">- 기본 배송료 : <input type="text" name="shopping">&nbsp;원</p>
		<p class="p2">- 무료 배송 : &nbsp;&nbsp;&nbsp;<input type="text" name="free">&nbsp;원 이상 구매 시 무료 배송</p>
		<p class="p2">- 배송 불가 지역</p>
		<p class="p2"><span id="address">주소가 표시됩니다</span><input type="button" name="search" value="우편번호 검색"><input type="button" name="add" value="추가"></p>
		<div id="addressArea"></div>
		<input type="button" name="delete" value="선택 삭제"><br>
		<h3>| 포인트 정책</h3>
		<p class="p2">- 가입 포인트 : 회원 가입 시 <input type="text" name="subscription">&nbsp;원 적립</p>
		<p class="p2">- 구매 포인트 : 구매 금액의 <input type="text" name="pursuit">&nbsp;% 적립</p>
		<h3>| 주문 취소 정책</h3>
		<p class="p2">- 무통장 입금 시 : 무통장 임금 주문 후 <input type="text" name="period">&nbsp;일간 입금하지 않을 시 주문 자동 취소</p>
		<h3>| 공통 배송 안내 등록</h3>
		<!-- Tab links -->
		<div class="tab1">
			<input type="button" class="tablinks1" data-oper="dPC" value="PC">
			<input type="button" class="tablinks1" data-oper="dMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="dPC" class="tabcontent1">
			<textarea name="dpc" id="dpc" rows="10" cols="100" style="width: 820px; height: 200px; display: none"></textarea>
		</div>
		<div id="dMobile" class="tabcontent1">
			<textarea name="dmobile" id="dmobile" rows="10" cols="100" style="width: 820px; height: 200px; display: none"></textarea>
		</div>
		<h3>| 공통 교환 및 반품 안내 등록</h3>
		<!-- Tab links -->
		<div class="tab2">
			<input type="button" class="tablinks2" data-oper="ePC" value="PC">
			<input type="button" class="tablinks2" data-oper="eMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="ePC" class="tabcontent2">
			<textarea name="epc" id="epc" rows="10" cols="100" style="width: 820px; height: 200px; display: none"></textarea>
		</div>
		<div id="eMobile" class="tabcontent2">
			<textarea name="emobile" id="emobile" rows="10" cols="100" style="width: 820px; height: 200px; display: none"></textarea>
		</div>
		<p class="p2"><input type="submit" data-oper="store" value="저장">&nbsp;<input type="submit" data-oper="cancel" value="취소"></p>
	</c:if>
	<c:if test="${!empty list}">
		<c:set var="shopping" value="${list.get(0)}" />
		<p class="p2">- 기본 배송료 : <input type="text" name="shopping" value="${shopping.value}">&nbsp;원</p>
		<c:set var="free" value="${list.get(1)}" />
		<p class="p2">- 무료 배송 : &nbsp;&nbsp;&nbsp;<input type="text" name="free" value="${free.value}">&nbsp;원 이상 구매 시 무료 배송</p>
		<p class="p2">- 배송 불가 지역</p>
		<p class="p2"><span id="address">주소가 표시됩니다</span><input type="button" name="search" value="우편번호 검색"><input type="button" name="add" value="추가"></p>
		<div id="addressArea">
	<c:if test="${list.size() > 9}">
		<c:forEach begin="9" end="${list.size() - 1}" var="index">
			<c:set var="policy" value="${list.get(index)}" />
			<span><input type="checkbox" name="area"><input type="hidden" name="post" value="${policy.value}">&nbsp;${policy.value}</span>
		</c:forEach>
	</c:if>
		</div>
		<input type="button" name="delete" value="선택 삭제"><br>
		<h3>| 포인트 정책</h3>
		<c:set var="subscription" value="${list.get(2)}" />
		<p class="p2">- 가입 포인트 : 회원 가입 시 <input type="text" name="subscription" value="${subscription.value}">&nbsp;원 적립</p>
		<c:set var="pursuit" value="${list.get(3)}" />
		<p class="p2">- 구매 포인트 : 구매 금액의 <input type="text" name="pursuit" value="${pursuit.value}">&nbsp;% 적립</p>
		<h3>| 주문 취소 정책</h3>
		<c:set var="period" value="${list.get(4)}" />
		<p class="p2">- 무통장 입금 시 : 무통장 임금 주문 후 <input type="text" name="period" value="${period.value}">&nbsp;일간 입금하지 않을 시 주문 자동 취소</p>
		<h3>| 공통 배송 안내 등록</h3>
		<!-- Tab links -->
		<div class="tab1">
			<input type="button" class="tablinks1" data-oper="dPC" value="PC">
			<input type="button" class="tablinks1" data-oper="dMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="dPC" class="tabcontent1">
			<c:set var="dpc" value="${list.get(5)}" />
			<textarea name="dpc" id="dpc" rows="10" cols="100" style="width: 820px; height: 200px; display: none">${dpc.value}</textarea>
		</div>
		<div id="dMobile" class="tabcontent1">
			<c:set var="dmobile" value="${list.get(6)}" />
			<textarea name="dmobile" id="dmobile" rows="10" cols="100" style="width: 820px; height: 200px; display: none">${dmobile.value}</textarea>
		</div>
		<h3>| 공통 교환 및 반품 안내 등록</h3>
		<!-- Tab links -->
		<div class="tab2">
			<input type="button" class="tablinks2" data-oper="ePC" value="PC">
			<input type="button" class="tablinks2" data-oper="eMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="ePC" class="tabcontent2">
			<c:set var="epc" value="${list.get(7)}" />
			<textarea name="epc" id="epc" rows="10" cols="100" style="width: 820px; height: 200px; display: none">${epc.value}</textarea>
		</div>
		<div id="eMobile" class="tabcontent2">
			<c:set var="emobile" value="${list.get(8)}" />
			<textarea name="emobile" id="emobile" rows="10" cols="100" style="width: 820px; height: 200px; display: none">${emobile.value}</textarea>
		</div>
		<p class="p2"><input type="submit" data-oper="store" value="저장">&nbsp;<input type="submit" data-oper="cancel" value="취소"></p>
	</c:if>
	</form>
	</section>
</div>
<%@ include file="../common/afooter.jsp" %>