package com.talanton.web.lala.policy.service;

import java.util.List;

import com.talanton.web.lala.policy.dto.Policy;

public interface PolicyService {
	List<Policy> getList();	// 정책 파라미터 목록을 가져온다.
	// 정책 파리미터 값들을 추가하거나 변경한다.
	void modify(String shopping, String free, String[] posts, String subscription, String pursuit,
				String period, String dpc, String dmobile, String epc, String emobile);
	String getValue(String category, String name);	// 정책 파라미터 값을 검색
}