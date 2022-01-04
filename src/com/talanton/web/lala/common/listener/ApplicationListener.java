package com.talanton.web.lala.common.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.talanton.web.lala.policy.dto.Policy;
import com.talanton.web.lala.policy.service.PolicyService;
import com.talanton.web.lala.policy.service.PolicyServiceImpl;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	CommonParameter cp = CommonParameter.getInstance();
    	String article_per_page = sce.getServletContext().getInitParameter("aricle_per_page");
    	cp.put("article_per_page", article_per_page);
    	String page_per_board = sce.getServletContext().getInitParameter("page_per_board");
    	cp.put("page_per_board", page_per_board);
    	String os_name = System.getProperty("os.name");
    	if(os_name.contains("Window")) {
    		String upload_path = sce.getServletContext().getInitParameter("window_upload_path");
    		cp.put("os.name", "Windows");
    		cp.put("upload_path", upload_path);
    	}
    	else {
    		String upload_path = sce.getServletContext().getInitParameter("linux_upload_path");
    		cp.put("os.name", "Linux");
    		cp.put("upload_path", upload_path);
    	}
    }
}