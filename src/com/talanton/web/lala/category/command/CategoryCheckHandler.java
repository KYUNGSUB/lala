package com.talanton.web.lala.category.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryCheckHandler implements CommandHandler {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String code = req.getParameter("code");
		CategoryService service = CategoryServiceImpl.getInstance();
		String check = service.codeCheck(code);
		req.setAttribute("result", check);
		return "/member/idCheck.jsp";
	}
}