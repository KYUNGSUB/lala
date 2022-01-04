package com.talanton.web.lala.product.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.talanton.web.lala.category.model.Category;
import com.talanton.web.lala.category.service.CategoryService;
import com.talanton.web.lala.category.service.CategoryServiceImpl;
import com.talanton.web.lala.common.command.CommandHandler;
import com.talanton.web.lala.common.listener.CommonParameter;
import com.talanton.web.lala.common.parameter.Constants;
import com.talanton.web.lala.pds.model.AddRequest;
import com.talanton.web.lala.pds.model.PdsItem;
import com.talanton.web.lala.pds.service.PdsItemNotFoundException;
import com.talanton.web.lala.pds.service.PdsItemService;
import com.talanton.web.lala.pds.service.PdsItemServiceImpl;
import com.talanton.web.lala.product.dto.PdsItemVO;
import com.talanton.web.lala.product.dto.Product;
import com.talanton.web.lala.product.dto.WriteForm;
import com.talanton.web.lala.product.service.ProductService;
import com.talanton.web.lala.product.service.ProductServiceImpl;

public class ProductUpdateHandler implements CommandHandler {
	private static final String FORM_VIEW = "/product/updateForm.jsp";

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, PdsItemNotFoundException {
		String pidStr = req.getParameter("pid");
		if(pidStr == null || pidStr.isEmpty()) {	// pid가 전달되지 않으면 에러 처리
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		int pid = Integer.parseInt(pidStr);
		ProductService service = ProductServiceImpl.getInstance();
		Product product = service.getProduct(pid);	// 상품 정보를 가져온다.
		if(product == null) {						// 상품 정보가 없으면 에러 처리
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if(product.isInfo()) {	// 상품 정보가 있으면 상품 정보를 가져온다.
			product.setInfoList(service.getInfoList(product.getPid()));
		}
		if(product.isOpt()) {	// 옵션 정보가 있으면 옵션 정보를 가져온다.
			product.setOptionList(service.getOptionList(product.getPid()));
		}
		product.setHistoryList(service.getHistoryList(product.getPid()));	// 상품 이력 정보를 가져온다.
		PdsItemService fService = PdsItemServiceImpl.getInstance();
		// 상품 이미지 정보를 가져온다.
		product.setPc_list(fService.getPdsItemByGid(Constants.PC_LIST_TYPE, product.getPid()).get(0));
		product.setPc_main(fService.getPdsItemByGid(Constants.PC_MAIN_TYPE, product.getPid()));
		product.setPc_expose(fService.getPdsItemByGid(Constants.PC_EXPOSE_TYPE, product.getPid()).get(0));
		product.setMobile_list(fService.getPdsItemByGid(Constants.MOBILE_LIST_TYPE, product.getPid()).get(0));
		product.setMobile_main(fService.getPdsItemByGid(Constants.MOBILE_MAIN_TYPE, product.getPid()));
		product.setMobile_expose(fService.getPdsItemByGid(Constants.MOBILE_EXPOSE_TYPE, product.getPid()).get(0));
		
		// 카테고리 정보를 가져온다.
		CategoryService cService = CategoryServiceImpl.getInstance();
		List<Category> cList = cService.getSecondList(Constants.STYLE_SHOP_CATEGORY);	// 스타일 숍 산하 2차 카테고리 목록을 가져온다.
		req.setAttribute("cList", cList);		// 2차 카테고리 정보를 뷰로 전달
		req.setAttribute("product", product);	// 상품 정보를 뷰로 전달
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PdsItem pdsItem = null;
		AddRequest addRequest = null;
		String contentType = req.getContentType();
		String viewPage = null;
		CommonParameter cp = CommonParameter.getInstance();
		String upload_path = (String)cp.get("upload_path");
		String uploadFolderPath = getFolder();
		if (contentType != null	&& contentType.toLowerCase().startsWith("multipart/")) {
			Collection<Part> parts = req.getParts();
			WriteForm wf = new WriteForm();
			for (Part part : parts) {
				if (part.getHeader("Content-Disposition").contains("filename=")) {
					addRequest = new AddRequest();
					addRequest.setFileSize(part.getSize());
					String fileName = part.getSubmittedFileName();
					addRequest.setFileName(fileName);
					addRequest.setFileType(getFileType(part.getName()));
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
						wf.addAddRequest(addRequest);
					}
				} else {
					String name = part.getName();
					if(name.equals("pid")) {
						wf.setPid(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("category1")) {
						wf.setCategory1(req.getParameter(name));
					} else if(name.equals("category2")) {
						wf.setCategory2(req.getParameter(name));
					} else if(name.equals("name")) {
						wf.setName(req.getParameter(name));
					} else if(name.equals("salePrice")) {
						wf.setSalePrice(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("price")) {
						wf.setPrice(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("maxPurchase")) {
						wf.setMaxPurchase(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("point")) {
						wf.setPoint(req.getParameter(name));
					} else if(name.equals("deposit")) {
						wf.setDeposit(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("fee")) {
						wf.setFee(req.getParameter(name));
					} else if(name.equals("delivery")) {
						wf.setDelivery(Integer.parseInt(req.getParameter(name)));
					} else if(name.equals("feature")) {
						wf.setFeature(req.getParameterValues(name));
					} else if(name.equals("infoBtn")) {
						wf.setInfoBtn(req.getParameter(name));
					} else if(name.equals("iname")) {
						wf.setIname(req.getParameterValues(name));
					} else if(name.equals("idescription")) {
						wf.setIdescription(req.getParameterValues(name));
					} else if(name.equals("optionBtn")) {
						wf.setOptionBtn(req.getParameter(name));
					} else if(name.equals("oname")) {
						wf.setOname(req.getParameterValues(name));
					} else if(name.equals("odescription")) {
						wf.setOdescription(req.getParameterValues(name));
					} else if(name.equals("oprice")) {
						wf.setOprice(req.getParameterValues(name));
					} else if(name.equals("pc_detail")) {
						wf.setPc_detail(req.getParameter(name));
					} else if(name.equals("introduction")) {
						wf.setIntroduction(req.getParameter(name));
					} else if(name.equals("mobile_detail")) {
						wf.setMobile_detail(req.getParameter(name));
					} else if(name.equals("dguide")) {
						wf.setDguide(req.getParameter(name));
					} else if(name.equals("pc_delivery")) {
						wf.setPc_delivery(req.getParameter(name));
					} else if(name.equals("mobile_delivery")) {
						wf.setMobile_delivery(req.getParameter(name));
					} else if(name.equals("exchange")) {
						wf.setExchange(req.getParameter(name));
					} else if(name.equals("pc_exchange")) {
						wf.setPc_exchange(req.getParameter(name));
					} else if(name.equals("mobile_exchange")) {
						wf.setMobile_exchange(req.getParameter(name));
					} else if(name.equals("memo")) {
						wf.setMemo(req.getParameter(name));
					} else if(name.equals("expose")) {
						wf.setExpose(req.getParameter(name));
					} else if(name.equals("userid")) {
						wf.setUserid(req.getParameter(name));
					} else if(name.contains("fileName")) {
						pdsItem = new PdsItem();
						pdsItem.setFileName(req.getParameter(name));
					} else if(name.contains("uuid")) {
						pdsItem.setUuid(req.getParameter(name));
					} else if(name.contains("uploadPath")) {
						pdsItem.setUploadPath(req.getParameter(name));
						wf.addPdsItem(pdsItem);
					}
				}
			}
			ProductService service = ProductServiceImpl.getInstance();
			int result = service.update(wf);
			res.sendRedirect("/product/list.do");
			viewPage = null;
		} else {
			req.setAttribute("errors", "Multipart 형식이 아님");
			viewPage = "/abanner/uploadFail.jsp";
		}
		return viewPage;
	}
	
	private String getFileType(String name) {
		if(name.equals("pclist")) {
			return Constants.PC_LIST_TYPE;
		} else if(name.contains("pcmain")) {
			return Constants.PC_MAIN_TYPE;
		} else if(name.equals("pcexpose")) {
			return Constants.PC_EXPOSE_TYPE;
		} else if(name.equals("mobilelist")) {
			return Constants.MOBILE_LIST_TYPE;
		} else if(name.contains("mobilemain")) {
			return Constants.MOBILE_MAIN_TYPE;
		} else {
			return Constants.MOBILE_EXPOSE_TYPE;
		}
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}