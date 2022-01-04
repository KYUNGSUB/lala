package com.talanton.web.lala.policy.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.policy.dto.Policy;
import com.talanton.web.lala.policy.service.PolicyService;
import com.talanton.web.lala.policy.service.PolicyServiceImpl;

public class PolicyManageHandler implements CommandHandler {
	private static final String FORM_VIEW = "/policy/manage.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
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

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		PolicyService service = PolicyServiceImpl.getInstance();
		List<Policy> list = service.getList();	// 파라미터 목록을 요청하여 가져온다.
		req.setAttribute("list", list);			// 뷰 페이지로 전달한다.
		return FORM_VIEW;						// /policy/manage.jsp에서 화면 응답
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String shopping = req.getParameter("shopping");
		String free = req.getParameter("free");
		String posts[] = req.getParameterValues("post");
		String subscription = req.getParameter("subscription");
		String pursuit = req.getParameter("pursuit");
		String period = req.getParameter("period");
		String dpc = req.getParameter("dpc");
		String dmobile = req.getParameter("dmobile");
		String epc = req.getParameter("epc");
		String emobile = req.getParameter("emobile");
		PolicyService service = PolicyServiceImpl.getInstance();
		service.modify(shopping, free, posts, subscription, pursuit,
				period, dpc, dmobile, epc, emobile);
		res.sendRedirect("manage.do");
		return null;
	}
}