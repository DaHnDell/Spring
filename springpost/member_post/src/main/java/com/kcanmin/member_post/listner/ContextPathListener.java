package com.kcanmin.member_post.listner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextPathListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath()+"/");
	}
	
}
