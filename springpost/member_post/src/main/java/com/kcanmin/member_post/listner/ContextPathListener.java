package com.kcanmin.member_post.listner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class ContextPathListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath()+"/");
	}
	
}
