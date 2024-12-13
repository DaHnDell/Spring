package com.kcanmin.member_post.servlet.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/index"})
public class Index extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/index.jsp").forward(req, resp);
	}
}
// 1 - 로그인 성공 시 성공한 id를 기억한다. 쿠키 활용 
// 2 - post vo, dao 만들기. crud 