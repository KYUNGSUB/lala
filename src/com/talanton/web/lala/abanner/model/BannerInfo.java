package com.talanton.web.lala.abanner.model;

import java.sql.Timestamp;

import com.talanton.web.lala.pds.model.PdsItem;

public class BannerInfo {
	private int info_id;
	private int bid;
	private String url;
	private String alt;
	private String target;
	private int loginBefore;	// default(0), 로그인 전(before:1), 로그인 후(after:2)
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private PdsItem pds;
	
	public BannerInfo() { }
	public BannerInfo(int info_id, int bid, String url, String alt, String target, int loginBefore,
			Timestamp createdAt, Timestamp modifiedAt, PdsItem pds) {
		super();
		this.info_id = info_id;
		this.bid = bid;
		this.url = url;
		this.alt = alt;
		this.target = target;
		this.loginBefore = loginBefore;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.pds = pds;
	}

	public int getInfo_id() {
		return info_id;
	}

	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getLoginBefore() {
		return loginBefore;
	}

	public void setLoginBefore(int loginBefore) {
		this.loginBefore = loginBefore;
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
	
	public PdsItem getPds() {
		return pds;
	}
	
	public void setPds(PdsItem pds) {
		this.pds = pds;
	}
}