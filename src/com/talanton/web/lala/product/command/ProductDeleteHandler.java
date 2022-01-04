package com.talanton.web.lala.product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.product.service.ProductService;
import com.talanton.web.lala.product.service.ProductServiceImpl;

public class ProductDeleteHandler implements CommandHandler {
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
		int pid = Integer.parseInt(req.getParameter("pid"));
		ProductService service = ProductServiceImpl.getInstance();
		if(service.remove(pid) == 1) {
			req.setAttribute("check", "success");
		} else {
			req.setAttribute("check", "fail");
		}
		return "/member/idCheck.jsp";
	}
}