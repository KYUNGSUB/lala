package com.talanton.web.lala.product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.product.service.ProductService;
import com.talanton.web.lala.product.service.ProductServiceImpl;

public class UpdateExposeHandler implements CommandHandler {

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
		String expose = req.getParameter("expose");
		ProductService service = ProductServiceImpl.getInstance();
		if(service.updateExpose(pid, expose) == 1) {	// pid와 상태 정보로 product 테이블 갱신
			req.setAttribute("check", "success");	// 성공 응답(success)
		} else {
			req.setAttribute("check", "fail");		// 실패 응답(fail)
		}
		return "/member/idCheck.jsp";
	}
}