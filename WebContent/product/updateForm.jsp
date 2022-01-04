<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/aheader.jsp" %>
<link rel="stylesheet" href="../css/product/register.css">
<script type="text/javascript" src="../smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/product/update.js"></script>
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
		<p class="p1">| 상품 관리 > 상품 수정</p>
		<h2>| 상품 수정</h2>
	<form action="update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pid" value="${product.pid}">
		<input type="hidden" name="category1" value="스타일 숍">
		<input type="hidden" name="userid" value="${product.userid}">
		<h3>| 상품 정보 입력</h3>
		<div>
			<select name="category2">
			<c:forEach var="category" items="${cList}">
			<c:if test="${category.name == product.category2}">
				<option value="${category.name}" selected="selected">${category.name}</option>
			</c:if>
			<c:if test="${category.name != product.category2}">
				<option value="${category.name}">${category.name}</option>
			</c:if>
			</c:forEach>
			</select>
			<input type="text" name="name" size="80" value="${product.name}" placeholder="상품명을 입력하세요.">
		</div>
		<table>
			<tr>
				<th>판매 가격</th>
				<td><input type="text" size="12" name="salePrice" value="${product.salePrice}">&nbsp;원</td>
				<th>정상 가격</th>
				<td><input type="text" size="12" name="price" value="${product.price}">&nbsp;원</td>
				<th>최대 구매 개수</th>
				<td><input type="text" size="6" name="maxPurchase" value="${product.maxPurchase}">&nbsp;개</td>
			</tr>
			<tr>
				<th>적립 포인트</th>
				<td colspan="5">
			<c:choose>
				<c:when test="${product.deposit == -1}">
					<label><input type="radio" name="point" value="basic" checked="checked">기본 포인트 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="point" value="apart">별도 포인트 적용&nbsp;</label>
					<span style="margin-right: 15px;">: 판매 가격의 <input type="text" size="10" name="deposit" disabled="disabled">&nbsp;%&nbsp;</span>
					<label><input type="radio" name="point" value="none">포인트 없음</label>
				</c:when>
				<c:when test="${product.deposit == 0}">
					<label><input type="radio" name="point" value="basic">기본 포인트 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="point" value="apart">별도 포인트 적용&nbsp;</label>
					<span style="margin-right: 15px;">: 판매 가격의 <input type="text" size="10" name="deposit" disabled="disabled">&nbsp;%&nbsp;</span>
					<label><input type="radio" name="point" value="none" checked="checked">포인트 없음</label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="point" value="basic">기본 포인트 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="point" value="apart" checked="checked">별도 포인트 적용&nbsp;</label>
					<span style="margin-right: 15px;">: 판매 가격의 <input type="text" size="10" name="deposit" value="${product.deposit}">&nbsp;%&nbsp;</span>
					<label><input type="radio" name="point" value="none">포인트 없음</label>
				</c:otherwise>
			</c:choose>
				</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td colspan="5">
			<c:choose>
				<c:when test="${product.delivery == -1}">
					<label><input type="radio" name="fee" value="basic" checked="checked">기본 배송비 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="fee" value="apart">별도 배송비 적용&nbsp;</label>
					<span style="margin-right: 15px;">: <input type="text" size="10" name="delivery" disabled="disabled">&nbsp;원&nbsp;</span>
					<label style="margin-left: 89px;"><input type="radio" name="fee" value="none">무료 배송</label>
				</c:when>
				<c:when test="${product.delivery == 0}">
					<label><input type="radio" name="fee" value="basic">기본 배송비 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="fee" value="apart">별도 배송비 적용&nbsp;</label>
					<span style="margin-right: 15px;">: <input type="text" size="10" name="delivery" disabled="disabled">&nbsp;원&nbsp;</span>
					<label style="margin-left: 89px;"><input type="radio" name="fee" value="none" checked="checked">무료 배송</label>
				</c:when>
				<c:otherwise>
					<label><input type="radio" name="fee" value="basic">기본 배송비 적용&nbsp;</label>
					<label style="margin-left: 15px;"><input type="radio" name="fee" value="apart" checked="checked">별도 배송비 적용&nbsp;</label>
					<span style="margin-right: 15px;">: <input type="text" size="10" name="delivery" value="${product.delivery}">&nbsp;원&nbsp;</span>
					<label style="margin-left: 89px;"><input type="radio" name="fee" value="none">무료 배송</label>
				</c:otherwise>
			</c:choose>
				</td>
			</tr>
			<tr>
				<th>상품 특성</th>
				<td colspan="5">
				<c:if test="${product.newp == true}">
					<label style="margin-right: 25px;"><input type="checkbox" name="feature" value="newp" checked="checked">&nbsp;신상품</label>
				</c:if>
				<c:if test="${product.newp == false}">
					<label style="margin-right: 25px;"><input type="checkbox" name="feature" value="newp">&nbsp;신상품</label>
				</c:if>
				<c:if test="${product.best == true}">
					<label style="margin-right: 25px;"><input type="checkbox" name="feature" value="best" checked="checked">&nbsp;BEST</label>
				</c:if>
				<c:if test="${product.best == false}">
					<label style="margin-right: 25px;"><input type="checkbox" name="feature" value="best">&nbsp;BEST</label>
				</c:if>
				<c:if test="${product.discount == true}">
					<label><input type="checkbox" name="feature" value="discount" checked="checked">&nbsp;할인</label>
				</c:if>
				<c:if test="${product.discount == false}">
					<label><input type="checkbox" name="feature" value="discount">&nbsp;할인</label>
				</c:if>
				</td>
			</tr>
			<tr>
				<th>
					<div style="text-align: left;">
						<p style="text-align: center;">상품 정보</p>
					<c:if test="${product.info == false}">
						<label style="text-align: left;"><input type="radio" name="infoBtn" value="no" checked="checked">미사용</label><br>
						<label><input type="radio" name="infoBtn" value="yes">사용</label>
					</c:if>
					<c:if test="${product.info == true}">
						<label style="text-align: left;"><input type="radio" name="infoBtn" value="no">미사용</label><br>
						<label><input type="radio" name="infoBtn" value="yes" checked="checked">사용</label>
					</c:if>
					</div>
				</th>
				<td colspan="5">
					<input style="width: 50px;" type="button" name="infoadd" value="추가">&nbsp;
					<input style="width: 50px;" type="button" name="infodel" value="삭제"><br>
					<div id="infoArea">
					<c:forEach var="info" items="${product.infoList}">
						<p><input type='checkbox' name='iitem'>&nbsp;
							<input type='text' name='iname' placeholder='항목명' value='${info.name}'>&nbsp;
							<input type='text' name='idescription' placeholder='설명' value='${info.description}'>
						</p>
					</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<div style="text-align: left;">
						<p style="text-align: center;">옵션</p>
					<c:if test="${product.opt == true}">
						<label style="text-align: left;"><input type="radio" name="optionBtn" value="no">미사용</label><br>
						<label><input type="radio" name="optionBtn" value="yes" checked="checked">사용</label>
					</c:if>
					<c:if test="${product.opt == false}">
						<label style="text-align: left;"><input type="radio" name="optionBtn" value="no" checked="checked">미사용</label><br>
						<label><input type="radio" name="optionBtn" value="yes">사용</label>
					</c:if>
					</div>
				</th>
				<td colspan="5">
					<input style="width: 50px;" type="button" name="optadd" value="추가">&nbsp;
					<input style="width: 50px;" type="button" name="optdel" value="삭제"><br>
					<div id="optionArea">
				<c:set var="gid" value="-1" />
				<c:forEach var="option" items="${product.optionList}">
					<c:if test="${gid == option.gid}">
						<p><span style='display: inline-block; width: 145px;'>&nbsp;</span>
							<input type='hidden' name='oname' value='${option.name}'>
							<input type='text' size='30' name='odescription' placeholder='설명' value='${option.description}'>
							<input type='text' size='10' name='oprice' placeholder='가격' value='${option.price}'>
							<input type='button' name='odelBtn' value='항목 삭제'></p>
					</c:if>
					<c:if test="${gid != option.gid}">
						<div><p><input type='checkbox' name='oitem'>
							<input type='text' size='10' name='oname' value='${option.name}' placeholder='옵션명'>
							<input type='text' size='30' name='odescription' value='${option.description}' placeholder='설명'>
							<input type='text' size='10' name='oprice' value='${option.price}' placeholder='가격'>
							<input type='button' name='oaddBtn' value='항목 추가'></p>
						</div>
						<c:set var="gid" value="${option.gid}" />
					</c:if>
				</c:forEach>	
					</div>
				</td>
			</tr>
		</table>
		<h3>| 상품 소개글</h3>
		<p style="margin-top: 5px;">
			<input type="text" size="114" maxlength="25" name="introduction" placeholder="25자 이내로 소개글을 입력하세요." value="${product.introduction}">
		</p>
		<!-- Tab links -->
		<div class="tab tab1">
			<input type="button" class="tablinks1" data-oper="t1PC" value="PC">
			<input type="button" class="tablinks1" data-oper="t1Mobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="t1PC" class="tabcontent tabcontent1">
			<table>
				<tr>
					<td style="width: 20%; text-align: right;">
						리스트 이미지<br>
						000*000
					</td>
				<c:if test="${!empty product.pc_list}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value='${product.pc_list}' />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="pclist" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${empty product.pc_list}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="pclist">
					</td>
				</c:if>
				</tr>
				<c:forEach var="i" begin="0" end="3">
				<tr>
				<c:if test="${i == 0}">
					<td rowspan="4" style="width: 20%; text-align: right;">
						상품 대표 이미지<br>
						000*000
					</td>
				</c:if>
				<c:if test="${product.pc_main.size() > i}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value="${product.pc_main.get(i)}" />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="pcmain${i+1}" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${product.pc_main.size() <= i}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="pcmain${i+1}">
					</td>
				</c:if>
				</tr>
				</c:forEach>
				<tr>
					<td style="width: 20%; text-align: right;">
						메인 노출 이미지<br>
						000*000
					</td>
				<c:if test="${!empty product.pc_expose}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value='${product.pc_expose}' />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="pcexpose" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${empty product.pc_expose}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="pcexpose">
					</td>
				</c:if>
				</tr>
			</table>
		</div>
		<div id="t1Mobile" class="tabcontent tabcontent1">
			<table>
				<tr>
					<td style="width: 20%; text-align: right;">
						리스트 이미지<br>
						000*000
					</td>
				<c:if test="${!empty product.mobile_list}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value='${product.mobile_list}' />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="mobilelist" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${empty product.mobile_list}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="mobilelist">
					</td>
				</c:if>
				</tr>
				<c:forEach var="i" begin="0" end="3">
				<tr>
				<c:if test="${i == 0}">
					<td rowspan="4" style="width: 20%; text-align: right;">
						상품 대표 이미지<br>
						000*000
					</td>
				</c:if>
				<c:if test="${product.mobile_main.size() > i}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value="${product.mobile_main.get(i)}" />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="mobilemain${i+1}" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${product.mobile_main.size() <= i}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="mobilemain${i+1}">
					</td>
				</c:if>
				</tr>
				</c:forEach>
				<tr>
					<td style="width: 20%; text-align: right;">
						메인 노출 이미지<br>
						000*000
					</td>
				<c:if test="${!empty product.mobile_expose}">
					<td class="imgTd" style="vertical-align: middle;">
						<c:set var="pds" value='${product.mobile_expose}' />
						<c:url value="/pds/download.do" var="displayUrl">
							<c:param name="fileName" value="${pds.uploadPath}\\${pds.uuid}_${pds.fileName}"></c:param>
						</c:url>
						<img src="<c:url value="${displayUrl}" />" alt="Product Image" style="width: 50px; height: 50px;">
						${pds.fileName}&nbsp;
						<a class="delFile" data-uuid="${pds.uuid}" data-path="${pds.uploadPath}"
							data-filename="${pds.fileName}" href="#">x</a>
					</td>
					<td style="width: 45%">
						<input type="file" name="mobileexpose" disabled="disabled">
					</td>
				</c:if>
				<c:if test="${empty product.mobile_expose}">
					<td class="imgTd" style="vertical-align: middle;"></td>
					<td style="width: 45%">
						<input type="file" name="mobileexpose">
					</td>
				</c:if>
				</tr>
			</table>
		</div>
		<h3>| 상품 상세 정보</h3>
		<!-- Tab links -->
		<div class="tab tab2">
			<input type="button" class="tablinks2" data-oper="ePC" value="PC">
			<input type="button" class="tablinks2" data-oper="eMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="ePC" class="tabcontent tabcontent2">
			<textarea name="pc_detail" id="pc_detail" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.pc_detail}</textarea>
		</div>
		<div id="eMobile" class="tabcontent tabcontent2">
			<textarea name="mobile_detail" id="mobile_detail" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.mobile_detail}</textarea>
		</div>
		<h3>| 배송 안내</h3>
		<p style="margin-top: 5px;">안내 선택 : 
		<c:if test="${empty product.pc_delivery}">
			<label><input type="radio" name="dguide" value="common" checked="checked">공통 배송 안내 노출</label>&nbsp;
			<label><input type="radio" name="dguide" value="indivisual">개별 배송 안내 작성</label>
		</c:if>
		<c:if test="${!empty product.pc_delivery}">
			<label><input type="radio" name="dguide" value="common">공통 배송 안내 노출</label>&nbsp;
			<label><input type="radio" name="dguide" value="indivisual" checked="checked">개별 배송 안내 작성</label>
		</c:if>
		</p>
		<!-- Tab links -->
		<div class="tab tab3">
			<input type="button" class="tablinks3" data-oper="fPC" value="PC">
			<input type="button" class="tablinks3" data-oper="fMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="fPC" class="tabcontent tabcontent3">
			<textarea name="pc_delivery" id="pc_delivery" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.pc_delivery}</textarea>
		</div>
		<div id="fMobile" class="tabcontent tabcontent3">
			<textarea name="mobile_delivery" id="mobile_delivery" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.mobile_delivery}</textarea>
		</div>
		<h3>| 교환 및 반품 안내</h3>
		<p style="margin-top: 5px;">안내 선택 : 
		<c:if test="${empty product.pc_exchange}">
			<label><input type="radio" name="exchange" value="common" checked="checked">공통 교환 및 반품 안내 노출</label>&nbsp;
			<label><input type="radio" name="exchange" value="indivisual">개별 교환 및 반품 안내 작성</label>
		</c:if>
		<c:if test="${!empty product.pc_exchange}">
			<label><input type="radio" name="exchange" value="common" checked="checked">공통 교환 및 반품 안내 노출</label>&nbsp;
			<label><input type="radio" name="exchange" value="indivisual" checked="checked">개별 교환 및 반품 안내 작성</label>
		</c:if>
		</p>
		<!-- Tab links -->
		<div class="tab tab4">
			<input type="button" class="tablinks4" data-oper="gPC" value="PC">
			<input type="button" class="tablinks4" data-oper="gMobile" value="Mobile">
		</div>
		<!-- Tab content -->
		<div id="gPC" class="tabcontent tabcontent4">
			<textarea name="pc_exchange" id="pc_exchange" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.pc_exchange}</textarea>
		</div>
		<div id="gMobile" class="tabcontent tabcontent4">
			<textarea name="mobile_exchange" id="mobile_exchange" rows="10" cols="100" style="width: 100%; height: 200px; display: none">${product.mobile_exchange}</textarea>
		</div>
		<div>
			<div class="box" style="float:left;">
				<h3>| 상품 이력 관리</h3>
				<div style="border: 1px solid black;">
				<c:forEach var="history" items="${product.historyList}">
					<p>
						<c:choose>
							<c:when test="${history.item == 1}">상품 등록</c:when>
							<c:when test="${history.item == 2}">상품 수정</c:when>
							<c:when test="${history.item == 3}">상품 삭제</c:when>
						</c:choose>
						<fmt:formatDate value="${history.timeAt}" type="both" pattern="yyyy-MM-dd HH:mm-ss" />
						${history.userid}
					</p>
				</c:forEach>
				</div>
			</div>
			<div class="box" style="float: right;">
				<h3>| 상품 메모</h3>
				<div>
					<textarea name="memo" rows="6" cols="65">${product.memo}</textarea>
				</div>
			</div>
		</div>
		<div style="clear: both">
			상품 노출 여부 : 
	<c:choose>
		<c:when test="${product.expose == '진열'}">
			<label><input type="radio" name="expose" value="진열" checked="checked">진열</label>&nbsp;
			<label><input type="radio" name="expose" value="숨김">숨김</label>&nbsp;
			<label><input type="radio" name="expose" value="품절">품절</label>
		</c:when>
		<c:when test="${product.expose == '숨김'}">
			<label><input type="radio" name="expose" value="진열">진열</label>&nbsp;
			<label><input type="radio" name="expose" value="숨김" checked="checked">숨김</label>&nbsp;
			<label><input type="radio" name="expose" value="품절">품절</label>
		</c:when>
		<c:otherwise>
			<label><input type="radio" name="expose" value="진열">진열</label>&nbsp;
			<label><input type="radio" name="expose" value="숨김">숨김</label>&nbsp;
			<label><input type="radio" name="expose" value="품절" checked="checked">품절</label>
		</c:otherwise>
	</c:choose>
		</div>
		<div id="submitBtn">
			<input type="submit" value="수정" data-oper="update">&nbsp;
			<input type="submit" value="취소" data-oper="cancel">
		</div>
	</form>
	</section>
</div>
<%@ include file="../common/afooter.jsp" %>