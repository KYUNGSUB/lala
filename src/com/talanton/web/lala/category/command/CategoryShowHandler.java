package com.talanton.web.lala.category.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;

public class CategoryShowHandler implements CommandHandler {
	private static final String FORM_VIEW = "/category/view.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {	// Ajax로 JSON 형태의 정보만 요청
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		CategoryService service = CategoryServiceImpl.getInstance();
		List<Category> list = service.getList();	// 카테고리 목록 정보를 가져온다.
		Gson gson = new Gson();
		JsonElement userObj = gson.toJsonTree(list);
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", "success");
		jsonObj.add("data", userObj);
		req.setAttribute("check", jsonObj.toString());	// 카테고리 목록 정보를 뷰로 전달
		return "/member/idCheck.jsp";
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		CategoryService service = CategoryServiceImpl.getInstance();
		List<Category> list = service.getList();	// 카테고리 목록 정보를 가져온다.
		req.setAttribute("list", list);				// 카테고리 목록 정보를 뷰로 전달
		return FORM_VIEW;
	}
}