package com.kcanmin.member_post.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.log4j.Log4j2;

@WebFilter({"/*"}) // 모든 요청
@Component
@Order
@Log4j2
public class CharsetFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리 - before
		request.setCharacterEncoding("utf-8");
		// 실제 처리부 - around?
		// chain.doFilter(request, response);
		// 안녕하세요? - after
		// log.info("filter 적용됨");
		chain.doFilter(request, response);
	}

}
