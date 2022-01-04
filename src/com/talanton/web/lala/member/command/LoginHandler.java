package com.talanton.web.lala.member.command;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.member.model.Member;
import com.talanton.web.lala.member.service.LoginService;

public class LoginHandler implements CommandHandler {
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
		return "/member/login.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException {
		String viewPage = null;
		String id = req.getParameter("id");
		String password = req.getParameter("pwd");
		LoginService loginService = LoginService.getInstance();
		Member member = loginService.login(id, password);
		HttpSession session = req.getSession();
		if(member != null) {
			session.setAttribute("member", member);
			session.setAttribute("id", id);
			res.sendRedirect("/index.do");
		}
		else {
			viewPage = "/member/login.jsp";
		}
		return viewPage;
	}
}