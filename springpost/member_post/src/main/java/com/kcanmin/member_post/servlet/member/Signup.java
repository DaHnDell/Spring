package com.kcanmin.member_post.servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.MemberService;
import service.MemberServiceImpl;
import vo.Member;

@WebServlet("/signup")
public class Signup extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String roadAddr = req.getParameter("roadAddress");
		String detailAddr = req.getParameter("address");
		Member member = Member
				.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.roadAddr(roadAddr)
				.detailAddr(detailAddr)
				.build();
		service.register(member);
		resp.sendRedirect("signup");
	}
	
}
