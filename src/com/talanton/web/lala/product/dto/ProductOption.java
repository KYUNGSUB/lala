package com.talanton.web.lala.product.dto;

import java.sql.Timestamp;

public class ProductOption {
	private int po_id;
	private int gid;
	private String name;
	private String description;
	private int price;
	private int pid;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public ProductOption() { }
	
	public ProductOption(int po_id, int gid, String name, String description, int price, int pid, Timestamp createdAt,
			Timestamp modifiedAt) {
		super();
		this.po_id = po_id;
		this.gid = gid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.pid = pid;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getPo_id() {
		return po_id;
	}

	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		return "ProductOption [po_id=" + po_id + ", gid=" + gid + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", pid=" + pid + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}