package com.talanton.web.lala.product.dto;

import java.sql.Timestamp;

public class ProductHistory {
	public static final int REGISTER = 1;
	public static final int MODIFY = 2;
	public static final int REMOVE = 3;
	
	private int hid;
	private int item;	// 1:상품등록, 2:상품수정, 3:상품삭제
	private Timestamp timeAt;
	private String userid;
	private int pid;
	
	public ProductHistory() { }
	
	public ProductHistory(int hid, int item, Timestamp timeAt, String userid, int pid) {
		super();
		this.hid = hid;
		this.item = item;
		this.timeAt = timeAt;
		this.userid = userid;
		this.pid = pid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public Timestamp getTimeAt() {
		return timeAt;
	}

	public void setTimeAt(Timestamp timeAt) {
		this.timeAt = timeAt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "ProductHistory [hid=" + hid + ", item=" + item + ", timeAt=" + timeAt + ", userid=" + userid + ", pid="
				+ pid + "]";
	}
}