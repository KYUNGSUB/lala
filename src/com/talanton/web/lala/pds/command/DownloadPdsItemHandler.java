package com.talanton.web.lala.pds.command;

import java.io.File;
import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.file.FileDownloadHelper;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.pds.service.PdsItemNotFoundException;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;

public class DownloadPdsItemHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws NamingException, IOException {
		String fileNamePath = req.getParameter("fileName");
		String[] fileData = fileNamePath.split("\\_");	// fileData[0]:uploadPath+uuid, fileData[1]: fileName 
		String uuid = fileData[0].substring(fileData[0].lastIndexOf("\\") + 1);
		try {
			// 응답 헤더 다운로드로 설정
			res.reset();
			
			PdsItemService service = PdsItemServiceImpl.getInstance();
			PdsItem item = service.getPdsItem(uuid);
			String fileName = new String(item.getFileName().getBytes("euc-kr"), "iso-8859-1");
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", 
					"attachment; filename=\"" + fileName+"\"");
			res.setHeader("Content-Transfer-Encoding", "binary");
			res.setContentLength((int)item.getFileSize());
			res.setHeader("Pragma", "no-cache;");
			res.setHeader("Expires", "-1;");

			CommonParameter cp = CommonParameter.getInstance();
			String realPath = (String)cp.get("upload_path") + File.separator + fileNamePath;
			FileDownloadHelper.copy(realPath, res.getOutputStream());
			
			res.getOutputStream().close();
			service.increaseCount(item.getUuid());
		} catch (PdsItemNotFoundException ex) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
	}
}