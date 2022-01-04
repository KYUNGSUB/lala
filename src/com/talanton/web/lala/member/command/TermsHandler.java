package com.talanton.web.lala.member.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.terms.dto.Terms;
import com.talanton.web.lala.terms.service.TermsService;
import com.talanton.web.lala.terms.service.TermsServiceImpl;

public class TermsHandler implements CommandHandler {
	private static final String FORM_VIEW = "/member/terms.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
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

	// 약관 동의 폼을 응답한다.
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		TermsService service = TermsServiceImpl.getInstance();
		List<Terms> list = service.getList();	// terms 테이블로부터 약관 목록을 얻는다.
		req.setAttribute("list", list);			// 뷰로 약관 목록을 전달
		return FORM_VIEW;						// 약관 동의를 위해 폼 페이지를 응답한다.
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String[] mAgree = req.getParameterValues("agreement");	// 동의(선택)이 여러 개일 경우 수정 필요
		Cookie cookie = new Cookie("terms", mAgree[0]);
		System.out.println("cookie: (terms, " + mAgree[0] + ")");
		res.addCookie(cookie);
		res.sendRedirect("/join.do");
		return null;
	}
}