package com.talanton.web.lala.category.model;

public class OptionInfo {
	private boolean expose;	// 카테고리 노출 여부
	private boolean gnb;	// GNB 노출 여부
	
	public OptionInfo() { }
	public OptionInfo(boolean expose, boolean gnb) {
		super();
		this.expose = expose;
		this.gnb = gnb;
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
	
	@Override
	public String toString() {
		return "OptionInfo [expose=" + expose + ", gnb=" + gnb + "]";
	}
}