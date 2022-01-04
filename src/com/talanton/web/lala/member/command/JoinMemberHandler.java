package com.talanton.web.lala.member.command;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.member.model.Member;
import com.talanton.web.lala.member.service.MemberService;
import com.talanton.web.lala.member.service.MemberServiceImpl;

public class JoinMemberHandler implements CommandHandler {
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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String url = null;
		Cookie[] cookies = req.getCookies();
		String terms = null;
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("terms")) {
					terms = cookie.getValue();
					Cookie newCookie = new Cookie("terms", terms);
					newCookie.setMaxAge(0);
					res.addCookie(newCookie);
					System.out.println("cookie: (" + cookie.getName() + ", " + terms + ")");
				}
			}
		}
		if(terms == null) {	// 서비스 이용 약관 동의를 거치지 않은 경우
			res.sendRedirect("/index.do");
		} else {
			req.setAttribute("marketing", terms);	// 마케팅 정보 제공 동의 여부를 실어줌
			url = "/member/join.jsp";
		}
		return url;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd1");
		String email = req.getParameter("mid") + "@" + req.getParameter("host");
		int grade = Integer.parseInt(req.getParameter("grade"));
		String marketing = req.getParameter("marketing");
		if(marketing.equals("true")) {
			marketing = "y";
		} else {
			marketing = "n";
		}
		Member member = new Member(name, id, pwd, email, grade, marketing);
		MemberService memberService = MemberServiceImpl.getInstance();
		int result = memberService.add(member);
		res.sendRedirect("/login.do?id=" + id);
		return null;
	}
}