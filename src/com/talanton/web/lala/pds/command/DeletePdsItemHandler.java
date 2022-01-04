package com.talanton.web.lala.pds.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;

public class DeletePdsItemHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws SQLException, NamingException {
		String uuid = req.getParameter("uuid");
		String path = req.getParameter("path");
		String fileName = req.getParameter("filename");
		PdsItem pds = new PdsItem();
		pds.setUuid(uuid);
		pds.setUploadPath(path);
		pds.setFileName(fileName);
		
		PdsItemService service = PdsItemServiceImpl.getInstance();
		service.removePdsItemByUuid(pds);
		req.setAttribute("check", "success");
		return "/member/idCheck.jsp";
	}
}