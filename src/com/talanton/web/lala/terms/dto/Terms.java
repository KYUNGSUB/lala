package com.talanton.web.lala.terms.dto;

import java.sql.Timestamp;

public class Terms {
	private int tid;		// 약관 아이디
	private String title;	// 약관 제목
	private String content;	// 내용
	private boolean expose;	// 노출 여부 : true(사용), false(사용 안함)
	private boolean mandatory;	// 필수 여부 : true(필수), false(선택)
	private Timestamp createdAt;	// 생성시간
	private Timestamp modifiedAt;	// 변경시간
	
	// 생성자
	public Terms() { }
	public Terms(int tid, String title, String content, boolean expose, boolean mandatory, Timestamp createdAt,
			Timestamp modifiedAt) {
		super();
		this.tid = tid;
		this.title = title;
		this.content = content;
		this.expose = expose;
		this.mandatory = mandatory;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	// Getter/Setter
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isExpose() {
		return expose;
	}
	public void setExpose(boolean expose) {
		this.expose = expose;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
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
	
	@Override
	public String toString() {
		return "Terms [tid=" + tid + ", title=" + title + ", content=" + content + ", expose=" + expose + ", mandatory="
				+ mandatory + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}