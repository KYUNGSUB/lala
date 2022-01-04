package com.talanton.web.lala.terms.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.terms.dto.Terms;
import com.talanton.web.lala.terms.service.TermsService;
import com.talanton.web.lala.terms.service.TermsServiceImpl;

public class TermsAddHandler implements CommandHandler {

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
		Terms term = new Terms();
		term.setTitle(req.getParameter("title"));
		term.setContent(req.getParameter("content"));
		String exposeStr = req.getParameter("expose");
		String mandatory = req.getParameter("mandatory");
		if(exposeStr.equals("yes")) {
			term.setExpose(true);
		} else {
			term.setExpose(false);
		}
		if(mandatory == null) {
			term.setMandatory(false);
		} else {
			term.setMandatory(true);
		}
		TermsService service = TermsServiceImpl.getInstance();
		int result = service.add(term);
		res.sendRedirect("list.do");
		return null;
	}
}