package com.talanton.web.lala.abanner.model;

public class ModifyForm {
	String kind;
	String position;
	String bid;
	String info_id;
	String location;
	String url;
	String alt;
	String target;
	String login;
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Banner toBanner() {
		Banner banner = new Banner();
		banner.setKind(Integer.parseInt(kind));
		banner.setPosition(Integer.parseInt(position));
		banner.setBid(Integer.parseInt(bid));
		if(location.equals("slide")) {
			banner.setLocation(1);
		} else if(location.equals("random")) {
			banner.setLocation(2);
		} else if(location.equals("login")) {
			banner.setLocation(3);
		} else {
			banner.setLocation(4);
		}
		
		return banner;
	}

	public BannerInfo toBannerInfo() {
		BannerInfo info = new BannerInfo();
		info.setUrl(url);
		info.setAlt(alt);
		info.setTarget(target);
		if(login.equals("default")) {
			info.setLoginBefore(0);
		} else if(login.equals("before")) {
			info.setLoginBefore(1);
		} else {
			info.setLoginBefore(2);
		}
		return info;
	}
}