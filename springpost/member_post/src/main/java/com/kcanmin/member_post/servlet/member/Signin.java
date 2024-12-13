package com.kcanmin.member_post.servlet.member;

import java.io.IOException;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.kcanmin.member_post.service.MemberService;
import com.kcanmin.member_post.service.MemberServiceImpl;

@WebServlet("/signin")
public class Signin extends HttpServlet{
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signin.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String rem = req.getParameter("remember-id");
//		System.out.println(rem);
		
		
//			cookie.setValue(service.findBy(id).getId());
//			resp.addCookie(cookie);
//			cookie.setMaxAge(0);
		if(service.login(id, pw)) {
			// login 성공
			HttpSession session =  req.getSession();
			session.setAttribute("member", service.findBy(id));
			
			// 쿠키의 id 기억 처리 여부
			if(rem != null) {
				Cookie cookie = new Cookie("remember-id", id);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				resp.addCookie(cookie);
			}else{
				// 아이디 기억 안할때 처리할 일들.
				Cookie[] cookies = req.getCookies();
				for(Cookie c : cookies) {
					if(c.getName().equals("rememeber-id")) {
						c.setMaxAge(0);
						resp.addCookie(c);
						break;
					}
				}
			}
			
			//url 파라미터 여부에 따른 리디렉션 페이지 지정
			String redirectURL = req.getContextPath() + "/index";
			String nexturl = req.getParameter("url");
			System.out.println(req.getParameter("url"));
			if(nexturl != null && !nexturl.equals("")) {
				redirectURL = URLDecoder.decode(nexturl, "utf-8");
			}
			
			resp.sendRedirect(redirectURL);
			
		}else{
			resp.sendRedirect("login?msg=fail");
		}
		
		// get 방식이어서 파라미터에 관한 것도 생성해야만 함.
	}
	
	
}
