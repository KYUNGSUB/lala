package com.talanton.web.lala.terms.service;

import java.util.List;

import com.talanton.web.lala.terms.dto.Terms;

public interface TermsService {
	List<Terms> getList();
	int add(Terms term);
	int modify(Terms term);
	int remove(int tid);
}