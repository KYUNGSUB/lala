package com.talanton.web.lala.abanner.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.talanton.web.lala.abanner.model.Banner;
import com.talanton.web.lala.abanner.model.BannerInfo;
import com.talanton.web.lala.abanner.model.ModifyForm;
import com.talanton.web.lala.abanner.service.ABannerService;
import com.talanton.web.lala.abanner.service.ABannerServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.pds.service.PdsItemNotFoundException;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;

public class ABannerModHandler implements CommandHandler {
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
		AddRequest addRequest = new AddRequest();
		String contentType = req.getContentType();
		String viewPage = null;
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		String uploadFolderPath = getFolder();
		if (contentType != null	&& contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = req.getParts();
			ModifyForm updateForm = new ModifyForm();
			for (Part part : parts) {
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					addRequest.setFileSize(part.getSize());
					String fileName = part.getSubmittedFileName();
					addRequest.setFileName(fileName);
					addRequest.setFileType("1");
					if (part.getSize() > 0) {
						// 업로드 폴더 생성
						File uploadPath = new File(upload_path, uploadFolderPath);
						if (uploadPath.exists() == false) {
							uploadPath.mkdirs();
						}	// make yyyy/MM/dd folder
						// IE has file path
						String uploadFileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
						
						// 파일이름 중복 방지
						UUID uuid = UUID.randomUUID();
						String uuidStr = uuid.toString();
						uploadFileName = uploadPath.getAbsolutePath() + File.separator + uuidStr + "_" + uploadFileName;
						addRequest.setUploadPath(uploadFolderPath);
						addRequest.setUuid(uuidStr);
						part.write(uploadFileName);
						part.delete();
					}
				} else {
					String name = part.getName();
					String value = req.getParameter(name);
					if(name.equals("kind")) {
						updateForm.setKind(value);
					}
					else if(name.equals("position")) {
						updateForm.setPosition(value);
					}
					else if(name.equals("bid")) {
						updateForm.setBid(value);
					}
					else if(name.equals("info_id")) {
						updateForm.setInfo_id(value);
						addRequest.setGid(Integer.parseInt(value));
					}
					else if(name.equals("location")) {
						updateForm.setLocation(value);
					}
					else if(name.equals("url")) {
						updateForm.setUrl(value);
					}
					else if(name.equals("alt")) {
						updateForm.setAlt(value);
					}
					else if(name.equals("target")) {
						updateForm.setTarget(value);
					}
					else if(name.equals("login")) {
						updateForm.setLogin(value);
					}
				}
			}
			// banner 정보 처리
			Banner banner = updateForm.toBanner();
			int result = updateBannerTable(banner);
			
			// bannerInfo 정보 처리
			BannerInfo bannerInfo = updateForm.toBannerInfo();
			result = updateBannerInfoTable(bannerInfo);
			
			// 파일 정보 처리
			updatePdsItemTable(addRequest);
			
			res.sendRedirect("/abanner/gnb.do?" + banner.getBannerLink());
			
			viewPage = null;
		} else {
			req.setAttribute("errors", "Multipart 형식이 아님");
			viewPage = "/abanner/uploadFail.jsp";
		}

		return viewPage;
	}
	
	private void updatePdsItemTable(AddRequest addRequest) throws SQLException, NamingException, PdsItemNotFoundException {
		if(addRequest.getUuid() == null) {	// 파일 변경 없음
			return;
		}
		PdsItemService service = PdsItemServiceImpl.getInstance();
		PdsItem oldPds = service.getPdsItemByGid("1", addRequest.getGid()).get(0);
		// 데이터베이스 삭제 및 추가
		service.removePdsItem(addRequest.getGid());
		service.add(addRequest);

		// 파일 삭제
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		String filePath = upload_path + File.separator + oldPds.getFullFileName();
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
	}

	private int updateBannerInfoTable(BannerInfo bannerInfo) {
		ABannerService service = ABannerServiceImpl.getInstance();
		int result = service.updateBannerInfo(bannerInfo);
		return result;
	}

	private int updateBannerTable(Banner banner) {
		ABannerService service = ABannerServiceImpl.getInstance();
		Banner exists = service.getBannerOnly(banner.getKind(), banner.getPosition());
		int result = -1;
		if (banner.getLocation() != exists.getLocation()) {	// 변경됨
			result = service.updateBanner(banner);
		}
		return result;
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}