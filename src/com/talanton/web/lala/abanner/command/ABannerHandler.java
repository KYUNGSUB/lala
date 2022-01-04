package com.talanton.web.lala.abanner.command;

import java.io.File;
import java.io.IOException;
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
import com.talanton.web.lala.abanner.model.WriteForm;
import com.talanton.web.lala.abanner.service.ABannerService;
import com.talanton.web.lala.abanner.service.ABannerServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;

public class ABannerHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException, ServletException {
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
		int kind = 1;
		int position = 1;
		String kindStr = req.getParameter("kind");
		if(kindStr != null) {
			kind = Integer.parseInt(kindStr);
		}
		String positionStr = req.getParameter("position");
		if(positionStr != null) {
			position = Integer.parseInt(positionStr);
		}
		ABannerService service = ABannerServiceImpl.getInstance();
		Banner banner = service.getBanner(kind, position);
		req.setAttribute("banner", banner);
		return "/abanner/agnb.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, NamingException, ServletException {
		AddRequest addRequest = new AddRequest();
		String contentType = req.getContentType();
		String viewPage = null;
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		String uploadFolderPath = getFolder();
		int banner_no = 0;
		if (contentType != null	&& contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = req.getParts();
			WriteForm writeForm = new WriteForm();
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
						addRequest.setGid(banner_no);
						addRequest.setUuid(uuidStr);
						part.write(uploadFileName);
						part.delete();
					}
				} else {
					String name = part.getName();
					String value = req.getParameter(name);
					if(name.equals("kind")) {
						writeForm.setKind(value);
					}
					else if(name.equals("position")) {
						writeForm.setPosition(value);
					}
					else if(name.equals("location")) {
						writeForm.setLocation(value);
					}
					else if(name.equals("url")) {
						writeForm.setUrl(value);
					}
					else if(name.equals("alt")) {
						writeForm.setAlt(value);
					}
					else if(name.equals("target")) {
						writeForm.setTarget(value);
					}
					else if(name.equals("login")) {
						writeForm.setLogin(value);
					}
				}
			}
			// banner 정보 처리
			Banner banner = processBannerTable(writeForm.toBanner());
			
			// bannerInfo 정보 처리
			BannerInfo bannerInfo = processBannerInfoTable(banner.getBid(), writeForm.toBannerInfo());
			
			// 파일 정보 처리
			addRequest.setGid(bannerInfo.getInfo_id());
			processPdsItemTable(addRequest);
			
			res.sendRedirect("/abanner/gnb.do?" + banner.getBannerLink());
			
			viewPage = null;
		} else {
			req.setAttribute("errors", "Multipart 형식이 아님");
			viewPage = "/abanner/uploadFail.jsp";
		}

		return viewPage;
	}
	
	private void processPdsItemTable(AddRequest addRequest) {
		PdsItemService service = PdsItemServiceImpl.getInstance();
		service.add(addRequest);
	}

	private BannerInfo processBannerInfoTable(int bid, BannerInfo bannerInfo) {
		ABannerService service = ABannerServiceImpl.getInstance();
		bannerInfo.setBid(bid);
		int info_id = service.addBannerInfo(bannerInfo);
		bannerInfo.setInfo_id(info_id);
		return bannerInfo;
	}

	private Banner processBannerTable(Banner banner) {
		ABannerService service = ABannerServiceImpl.getInstance();
		Banner exists = service.getBannerOnly(banner.getKind(), banner.getPosition());
		if(exists == null) {	// 존재하지 않음(추가 필요)
			int rowId = service.addBanner(banner);
			banner.setBid(rowId);
		} else if (banner.getLocation() != exists.getLocation()) {				// 기존에 존재함
			int result = service.updateBanner(banner);
			banner.setBid(exists.getBid());
		} else {
			banner.setBid(exists.getBid());
		}
		return banner;
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}