package com.talanton.web.lala.product.dto;

import java.sql.Timestamp;

public class ProductInfo {
	private int pi_id;
	private String name;
	private String description;
	private int pid;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public ProductInfo() { }
	
	public ProductInfo(int pi_id, String name, String description, int pid, Timestamp createdAt, Timestamp modifiedAt) {
		super();
		this.pi_id = pi_id;
		this.name = name;
		this.description = description;
		this.pid = pid;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getPi_id() {
		return pi_id;
	}

	public void setPi_id(int pi_id) {
		this.pi_id = pi_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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
		return "ProductInfo [pi_id=" + pi_id + ", name=" + name + ", description=" + description + ", pid=" + pid
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}