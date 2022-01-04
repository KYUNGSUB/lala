package com.talanton.web.lala.terms.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.terms.dto.Terms;
import com.talanton.web.lala.terms.service.TermsService;
import com.talanton.web.lala.terms.service.TermsServiceImpl;

public class TermsRemoveHandler implements CommandHandler {

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
		int tid = Integer.parseInt(req.getParameter("tid"));
		TermsService service = TermsServiceImpl.getInstance();
		int result = service.remove(tid);
		res.sendRedirect("list.do");
		return null;
	}
}