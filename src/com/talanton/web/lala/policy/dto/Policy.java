package com.talanton.web.lala.policy.dto;

import java.sql.Timestamp;

public class Policy {
	private int code;
	private String category;
	private String name;
	private String value;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	// 생성자
	public Policy() { }
	public Policy(int code, String category, String name, String value, Timestamp createdAt, Timestamp modifiedAt) {
		this.code = code;
		this.category = category;
		this.name = name;
		this.value = value;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	// Getter/Setter
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
		return "Policy [code=" + code + ", category=" + category + ", name=" + name + ", value=" + value
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}