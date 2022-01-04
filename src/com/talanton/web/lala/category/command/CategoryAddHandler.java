package com.talanton.web.lala.category.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryAddHandler implements CommandHandler {

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
		Category category = new Category();
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		String exposeStr = req.getParameter("expose");
		String gnbStr = req.getParameter("gnb");
		boolean expose = exposeStr.equals("yes") ? true: false;
		boolean gnb = gnbStr.equals("yes") ? true: false;
		category.setName(name);
		category.setCode(code);
		category.setExpose(expose);
		category.setGnb(gnb);
		String parent = req.getParameter("parent");
		if(parent == null) {
			category.setStep(1);	// 1차
		} else {
			category.setStep(2);	// 2차
			category.setParent(parent);
		}
		CategoryService service = CategoryServiceImpl.getInstance();
		int result = service.add(category);
		res.sendRedirect("/category/show.do");
		return null;
	}
}