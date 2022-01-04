package com.talanton.web.lala.member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.member.service.MemberService;
import com.talanton.web.lala.member.service.MemberServiceImpl;

public class IdCheckHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		MemberService service = MemberServiceImpl.getInstance();
		String check = service.idCheck(id);
		req.setAttribute("result", check);
		return "/member/idCheck.jsp";
	}
}