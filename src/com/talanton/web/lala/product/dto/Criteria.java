package com.talanton.web.lala.product.dto;

import java.util.Arrays;

public class Criteria {
	private int pageNum;		// 현재 페이지 번호
	private int amount;			// 한 페이지에 보여줄 상품 수
	
	private String type;		// 검색 유형 : name(상품명), regid(등록자)
	private String keyword;		// 검색어
	private String category1;	// 1차 카테고리
	private String category2;	// 2차 카테고리
	private int priceFrom;		// 판매가격 하한가
	private int priceTo;		// 판매가격 상한가
	private String regFrom;		// 상품 등록일 하한일
	private String regTo;		// 상품 등록일 상한일
	private String expose[];	// 진열여부 : 진열(show), 품절(out), 숨김(hide)
	private String detail;		// 상세 검색 여부 : yes(상세 검색), no

	// 생성자
	public Criteria() {	// 기본적으로 1 페이지, 10개
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {	// 정해준 페이지, 갯수
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getPageStart() {	// 페이징을 위해 테이블에서 가져올 시작점
		return (pageNum - 1) * amount;
	}
	
	// Getter/Setter
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public int getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}

	public int getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}

	public String getRegFrom() {
		return regFrom;
	}

	public void setRegFrom(String regFrom) {
		this.regFrom = regFrom;
	}

	public String getRegTo() {
		return regTo;
	}

	public void setRegTo(String regTo) {
		this.regTo = regTo;
	}

	public String[] getExpose() {
		return expose;
	}

	public void setExpose(String[] expose) {
		this.expose = expose;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SearchForm [type=" + type + ", keyword=" + keyword + ", category1=" + category1 + ", category2="
				+ category2 + ", priceFrom=" + priceFrom + ", priceTo=" + priceTo + ", regFrom=" + regFrom + ", regTo="
				+ regTo + ", expose=" + Arrays.toString(expose) + ", regTo=" + regTo + ", pageNum=" + pageNum + ", amount=" + amount + "]";
	}
}