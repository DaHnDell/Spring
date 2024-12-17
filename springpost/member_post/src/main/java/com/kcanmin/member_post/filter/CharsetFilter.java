package com.kcanmin.member_post.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter({"/*"}) // 모든 요청
public class CharsetFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		request.setCharacterEncoding("utf-8");
		// 실제 처리부
		chain.doFilter(request, response);
		// 안녕하세요?
	}
	
	

}
