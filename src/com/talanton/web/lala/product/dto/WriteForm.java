package com.talanton.web.lala.product.dto;

import java.util.ArrayList;
import java.util.List;

import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.PdsItem;

public class WriteForm {
	private int pid;
	private String category1;
	private String category2;
	private String name;
	private int price;
	private int salePrice;
	private int maxPurchase;
	private String point;
	private int deposit;
	private String fee;
	private int delivery;
	private String[] feature;
	private String infoBtn;		// no(미사용:false), yes(사용:true)
	private String[] iname;
	private String[] idescription;
	private String optionBtn;	// no(미사용:false), yes(사용:true)
	private String[] oname;
	private String[] odescription;
	private String[] oprice;
	private String userid;
	private int readCount;
	private String introduction;
	private List<AddRequest> list = new ArrayList<AddRequest>();
	private String pc_detail;
	private String mobile_detail;
	private String dguide;
	private String pc_delivery;
	private String mobile_delivery;
	private String exchange;
	private String pc_exchange;
	private String mobile_exchange;
	private String memo;
	private String expose;
	private List<PdsItem> attachList = new ArrayList<PdsItem>();
	
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
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public String[] getFeature() {
		return feature;
	}
	public void setFeature(String[] feature) {
		this.feature = feature;
	}
	public String getInfoBtn() {
		return infoBtn;
	}
	public void setInfoBtn(String infoBtn) {
		this.infoBtn = infoBtn;
	}
	public String getOptionBtn() {
		return optionBtn;
	}
	public void setOptionBtn(String optionBtn) {
		this.optionBtn = optionBtn;
	}
	public String[] getIname() {
		return iname;
	}
	public void setIname(String[] iname) {
		this.iname = iname;
	}
	public String[] getIdescription() {
		return idescription;
	}
	public void setIdescription(String[] idescription) {
		this.idescription = idescription;
	}
	public String[] getOname() {
		return oname;
	}
	public void setOname(String[] oname) {
		this.oname = oname;
	}
	public String[] getOdescription() {
		return odescription;
	}
	public void setOdescription(String[] odescription) {
		this.odescription = odescription;
	}
	public String[] getOprice() {
		return oprice;
	}
	public void setOprice(String[] oprice) {
		this.oprice = oprice;
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
	public List<AddRequest> getList() {
		return list;
	}
	public void setList(List<AddRequest> list) {
		this.list = list;
	}
	public void addAddRequest(AddRequest addRequest) {
		list.add(addRequest);
	}
	public AddRequest getAddRequest(int index) {
		return list.get(index);
	}
	public int sizeOfList() {
		return list.size();
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
	public String getDguide() {
		return dguide;
	}
	public void setDguide(String dguide) {
		this.dguide = dguide;
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
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
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
	public List<PdsItem> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<PdsItem> attachList) {
		this.attachList = attachList;
	}
	
	public Product toProduct() {
		Product product = new Product();
		if(pid != 0) {	// 수정인 경우
			product.setPid(pid);
		}
		product.setCategory1(category1);
		product.setCategory2(category2);
		product.setName(name);
		product.setPrice(price);
		product.setSalePrice(salePrice);
		product.setMaxPurchase(maxPurchase);
		if(point.equals("basic")) {
			product.setDeposit(-1);			// 기본 포인트 적용
		} else if(point.equals("apart")) {
			product.setDeposit(deposit);	// 개별 포인트 적용
		} else {
			product.setDeposit(0);			// 포인트 없음
		}
		if(fee.equals("basic")) {
			product.setDelivery(-1);		// 기본 배송비
		} else if(fee.equals("apart")) {
			product.setDelivery(delivery);	// 개별 배송비
		} else {
			product.setDelivery(0);	// 무료 배송비
		}
		for(String f : feature) {
			if(f.equals("newp")) {
				product.setNewp(true);
			} else if(f.equals("best")) {
				product.setBest(true);
			} else {
				product.setDiscount(true);
			}
		}
		if(infoBtn.equals("no")) {
			product.setInfo(false);
		} else {
			product.setInfo(true);
		}
		if(optionBtn.equals("no")) {
			product.setOpt(false);
		} else {
			product.setOpt(true);
		}
		product.setUserid(userid);
		product.setIntroduction(introduction);
		product.setPc_detail(pc_detail);
		product.setMobile_detail(mobile_detail);
		if(dguide.equals("indivisual")) {	// 개별 배송 안내
			product.setPc_delivery(pc_delivery);
			product.setMobile_delivery(mobile_delivery);
		}
		if(exchange.equals("indivisual")) {
			product.setPc_exchange(pc_exchange);
			product.setMobile_exchange(mobile_exchange);
		}
		product.setMemo(memo);
		product.setExpose(expose);
		return product;
	}
	
	public List<ProductInfo> toProductInfo() {
		List<ProductInfo> list = new ArrayList<>();
		for(int i = 0;i < iname.length;i++) {
			ProductInfo info = new ProductInfo();
			info.setName(iname[i]);
			info.setDescription(idescription[i]);
			info.setPid(pid);
			list.add(info);
		}
		return list;
	}
	
	public List<ProductOption> toProductOption() {
		List<ProductOption> list = new ArrayList<>();
		String name = null;
		int gid = -1;
		for(int i = 0;i < oname.length;i++) {
			ProductOption option = new ProductOption();
			if(!oname[i].equals(name)) {
				gid++;
				name = oname[i];
			}
			option.setGid(gid);
			option.setName(oname[i]);
			option.setDescription(odescription[i]);
			option.setPrice(Integer.parseInt(oprice[i]));
			option.setPid(pid);
			list.add(option);
		}
		return list;
	}
	
	public void addPdsItem(PdsItem pdsItem) {
		attachList.add(pdsItem);
	}
}