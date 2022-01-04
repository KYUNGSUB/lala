package com.talanton.web.lala.terms.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.terms.dto.Terms;
import com.talanton.web.lala.terms.service.TermsService;
import com.talanton.web.lala.terms.service.TermsServiceImpl;

public class TermsListHandler implements CommandHandler {
	private static final String FORM_VIEW = "/terms/list.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		TermsService service = TermsServiceImpl.getInstance();
		List<Terms> list = service.getList();
		req.setAttribute("list", list);
		return FORM_VIEW;
	}
}