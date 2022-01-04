package com.talanton.web.lala.category.model;

import java.sql.Timestamp;
import java.util.List;

public class Category {
	private String name;	// 카테고리 이름
	private String code;	// 카테고리 코드 (primary Key)
	private boolean expose;	// 카테고리 노출 여부
	private boolean gnb;	// GNB 진열 여부
	private int step;		// 단계(1차 또는 2차)
	private int seq;		// 배치 순서
	private String parent;	// 1차 카테고리 코드(2차 카테고리 경우)
	private Timestamp createdAt;	// 생성시간
	private Timestamp modifiedAt;	// 수정시간
	private List<Category> list;	// 자식 카테고리 목록
	
	// 생성자
	public Category() { }
	
	public Category(String name, String code, boolean expose, boolean gnb, int step, int seq, String parent,
			Timestamp createdAt, Timestamp modifiedAt) {
		this.name = name;
		this.code = code;
		this.expose = expose;
		this.gnb = gnb;
		this.step = step;
		this.seq = seq;
		this.parent = parent;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	// Getter/Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isExpose() {
		return expose;
	}

	public void setExpose(boolean expose) {
		this.expose = expose;
	}

	public boolean isGnb() {
		return gnb;
	}

	public void setGnb(boolean gnb) {
		this.gnb = gnb;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}
	
	public boolean hasSecond() {
		return list != null && ! list.isEmpty();
	}
}