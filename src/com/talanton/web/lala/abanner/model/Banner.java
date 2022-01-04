package com.talanton.web.lala.abanner.model;

import java.sql.Timestamp;
import java.util.List;

public class Banner {
	private int bid;
	private int kind;		// 배너 종류(kind) : 1(GNB), 2(메인), 3(스타일숍 리스트), 4(오픈숍 리스트),
							//		5(서브 메뉴), 6(커뮤니티 리스트), 7(고객센터), 8(상품 주문 완료)
	private int position;	// 배너 위치(position) : 1(상단), 2(오른쪽), 3(왼쪽), 4(하단)
	private int location;	// 노출방식(location) : 1(슬라이더), 2(랜덤), 3(로그인 전/후), 4(노출하지 않음)
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private List<BannerInfo> infoList;
	
	public Banner() { }
	
	public Banner(int bid, int kind, int position, int location, Timestamp createdAt, Timestamp modifiedAt) {
		super();
		this.bid = bid;
		this.kind = kind;
		this.position = position;
		this.location = location;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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
	
	public List<BannerInfo> getInfoList() {
		return infoList;
	}
	
	public void setInfoList(List<BannerInfo> list) {
		this.infoList = list;
	}

	public boolean isHasInfo() {
		return infoList != null && ! infoList.isEmpty();
	}
	
	public boolean isHasInfo(int index) {
		return infoList != null && infoList.get(index) != null;
	}

	public String getBannerLink() {
		return "kind=" + kind + "&position=" + position;
	}
}