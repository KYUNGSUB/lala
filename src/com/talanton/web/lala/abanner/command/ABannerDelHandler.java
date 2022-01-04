package com.talanton.web.lala.abanner.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.abanner.service.ABannerService;
import com.talanton.web.lala.abanner.service.ABannerServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.pds.service.PdsItemNotFoundException;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;

public class ABannerDelHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException, ServletException, SQLException, PdsItemNotFoundException {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException, ServletException, SQLException, PdsItemNotFoundException {
		String kind = req.getParameter("kind");
		String position = req.getParameter("position");
		int info_id = Integer.parseInt(req.getParameter("info_id"));
		
		ABannerService service = ABannerServiceImpl.getInstance();
		int result = service.removeBannerInfo(info_id);
			
		// 파일 정보 처리
		removePdsItemTable(info_id);
			
		res.sendRedirect("/abanner/gnb.do?kind=" + kind + "&position=" + position);
		return null;
	}
	
	private void removePdsItemTable(int info_id) throws SQLException, NamingException, PdsItemNotFoundException {
		PdsItemService service = PdsItemServiceImpl.getInstance();
		PdsItem oldPds = service.getPdsItemByGid("1", info_id).get(0);
		// 데이터베이스 삭제 및 추가
		service.removePdsItem(info_id);

		// 파일 삭제
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		String filePath = upload_path + File.separator + oldPds.getFullFileName();
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
	}
}