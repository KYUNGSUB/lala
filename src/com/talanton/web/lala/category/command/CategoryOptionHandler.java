package com.talanton.web.lala.category.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.talanton.web.lala.category.model.OptionInfo;
import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryOptionHandler implements CommandHandler {
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
		String code = req.getParameter("code");
		CategoryService service = CategoryServiceImpl.getInstance();
		OptionInfo option = service.getOptionInfo(code);
		Gson gson = new Gson();	// Gson 라이브러리를 사용
		JsonObject jsonObj = new JsonObject();	// JsonObject 객체를 생성
		jsonObj.addProperty("expose", option.isExpose());	// 필드로 저장
		jsonObj.addProperty("gnb", option.isGnb());
		req.setAttribute("check", gson.toJson(jsonObj));	// request 속성에 JSON string 형태로 변환하여 저장
		return "/member/idCheck.jsp";	// 뷰로 전달한다.
	}
}