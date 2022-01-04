package com.talanton.web.lala.category.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryModifyHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String code = req.getParameter("code");
		String exposeStr = req.getParameter("expose");
		boolean expose = exposeStr.equals("yes") ? true: false;
		String gnbStr = req.getParameter("gnb");
		CategoryService service = CategoryServiceImpl.getInstance();
		if(gnbStr == null) {	// 2차 카테고리 정보 변경 -> 노출 여부 변경
			int result = service.modify(code, expose);	// 메소드 오버로딩 적용
		} else {	// 1차 카테고리 정보 변경 -> 노출 여부 및 GNB 노출여부 변경
			boolean gnb = gnbStr.equals("yes") ? true: false;
			int result = service.modify(code, expose, gnb);
		}
		res.sendRedirect("/category/show.do");
		return null;
	}
}