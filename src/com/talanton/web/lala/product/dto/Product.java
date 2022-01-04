package com.talanton.web.lala.product.dto;

import java.sql.Timestamp;
import java.util.List;

import com.talanton.web.lala.pds.model.PdsItem;

public class Product {
	private int pid;
	private String category1;
	private String category2;
	private String name;
	private int price;
	private int salePrice;
	private int maxPurchase;
	private int deposit;
	private int delivery;
	private boolean newp;
	private boolean best;
	private boolean discount;
	private boolean info;		// no(미사용:false), yes(사용:true)
	private List<ProductInfo> infoList;
	private boolean opt;		// no(미사용:false), yes(사용:true)
	private List<ProductOption> optionList;
	private String userid;
	private int readCount;
	private String introduction;
	private PdsItem pc_list;
	private List<PdsItem> pc_main;
	private PdsItem pc_expose;
	private PdsItem mobile_list;
	private List<PdsItem> mobile_main;
	private PdsItem mobile_expose;
	private String pc_detail;
	private String mobile_detail;
	private String pc_delivery;
	private String mobile_delivery;
	private String pc_exchange;
	private String mobile_exchange;
	private List<ProductHistory> historyList;
	private String memo;
	private String expose;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getMaxPurchase() {
		return maxPurchase;
	}
	public void setMaxPurchase(int maxPurchase) {
		this.maxPurchase = maxPurchase;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public boolean isNewp() {
		return newp;
	}
	public void setNewp(boolean newp) {
		this.newp = newp;
	}
	public boolean isBest() {
		return best;
	}
	public void setBest(boolean best) {
		this.best = best;
	}
	public boolean isDiscount() {
		return discount;
	}
	public void setDiscount(boolean discount) {
		this.discount = discount;
	}
	public boolean isInfo() {
		return info;
	}
	public void setInfo(boolean info) {
		this.info = info;
	}
	public List<ProductInfo> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<ProductInfo> infoList) {
		this.infoList = infoList;
	}
	public boolean isOpt() {
		return opt;
	}
	public void setOpt(boolean opt) {
		this.opt = opt;
	}
	public List<ProductOption> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<ProductOption> optionList) {
		this.optionList = optionList;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public PdsItem getPc_list() {
		return pc_list;
	}
	public void setPc_list(PdsItem pc_list) {
		this.pc_list = pc_list;
	}
	public List<PdsItem> getPc_main() {
		return pc_main;
	}
	public void setPc_main(List<PdsItem> pc_main) {
		this.pc_main = pc_main;
	}
	public PdsItem getPc_expose() {
		return pc_expose;
	}
	public void setPc_expose(PdsItem pc_expose) {
		this.pc_expose = pc_expose;
	}
	public PdsItem getMobile_list() {
		return mobile_list;
	}
	public void setMobile_list(PdsItem mobile_list) {
		this.mobile_list = mobile_list;
	}
	public List<PdsItem> getMobile_main() {
		return mobile_main;
	}
	public void setMobile_main(List<PdsItem> mobile_main) {
		this.mobile_main = mobile_main;
	}
	public PdsItem getMobile_expose() {
		return mobile_expose;
	}
	public void setMobile_expose(PdsItem mobile_expose) {
		this.mobile_expose = mobile_expose;
	}
	public String getPc_detail() {
		return pc_detail;
	}
	public void setPc_detail(String pc_detail) {
		this.pc_detail = pc_detail;
	}
	public String getMobile_detail() {
		return mobile_detail;
	}
	public void setMobile_detail(String mobile_detail) {
		this.mobile_detail = mobile_detail;
	}
	public String getPc_delivery() {
		return pc_delivery;
	}
	public void setPc_delivery(String pc_delivery) {
		this.pc_delivery = pc_delivery;
	}
	public String getMobile_delivery() {
		return mobile_delivery;
	}
	public void setMobile_delivery(String mobile_delivery) {
		this.mobile_delivery = mobile_delivery;
	}
	public String getPc_exchange() {
		return pc_exchange;
	}
	public void setPc_exchange(String pc_exchange) {
		this.pc_exchange = pc_exchange;
	}
	public String getMobile_exchange() {
		return mobile_exchange;
	}
	public void setMobile_exchange(String mobile_exchange) {
		this.mobile_exchange = mobile_exchange;
	}
	public List<ProductHistory> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<ProductHistory> historyList) {
		this.historyList = historyList;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getExpose() {
		return expose;
	}
	public void setExpose(String expose) {
		this.expose = expose;
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
}