package com.talanton.web.lala.amember.command;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.member.model.Member;
import com.talanton.web.lala.member.service.LoginService;

public class ALoginHandler implements CommandHandler {
	private static final int ADMINISTRATOR = 3;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		return "/amember/alogin.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		String viewPage = null;
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		LoginService loginService = LoginService.getInstance();
		Member member = loginService.login(id, password);
		if(member != null && member.getGrade() >= ADMINISTRATOR) {
			HttpSession session = req.getSession();
			session.setAttribute("amember", member);
			session.setAttribute("aid", id);
			res.sendRedirect("/aindex.do");
		}
		else {
			viewPage = "/amember/alogin.jsp";
		}
		return viewPage;
	}
}