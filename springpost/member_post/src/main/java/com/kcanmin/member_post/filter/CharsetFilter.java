package com.kcanmin.member_post.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter({"/*"}) // 모든 요청
public class CharsetFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		request.setCharacterEncoding("utf-8");
		// 실제 처리부
		chain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}
	
	

}
