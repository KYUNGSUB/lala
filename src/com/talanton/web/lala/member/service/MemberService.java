package com.talanton.web.lala.member.service;

import com.talanton.web.lala.member.model.Member;

public interface MemberService {
	int add(Member member);
//	boolean updateDeviceId(String uid, String deviceId);
//	ListMember getMemberList(int pageNumber);
//	Member getMember(String id) throws MemberNotFoundException;
	String idCheck(String id);
//	boolean emailCheck(String email);
//	String getDeviceId(String name, String phone);
//	void cryptPassword();
}