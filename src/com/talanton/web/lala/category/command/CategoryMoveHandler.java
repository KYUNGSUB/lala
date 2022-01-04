package com.talanton.web.lala.category.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.category.model.MoveRequest;
import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryMoveHandler implements CommandHandler {

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
		int step = Integer.parseInt(req.getParameter("step"));
		String direction = req.getParameter("direction");
		int seq = Integer.parseInt(req.getParameter("seq"));
		String code = req.getParameter("code");
		String parent = req.getParameter("parent");
		MoveRequest mr = new MoveRequest(step, direction, seq, code, parent);
		System.out.println(mr);
		CategoryService service = CategoryServiceImpl.getInstance();
		service.moveCatgory(mr);
		res.sendRedirect("/category/show.do");
		return null;
	}
}