package com.kcanmin.member_post.servlet.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Criteria;
import dto.PageDto;
import service.PostService;
import service.PostServiceImpl;
import vo.Post;

@WebServlet("/post/list")
public class PostList extends HttpServlet{

	private PostService service = new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// param getter
//		int cate = setDefaultValue(req.getParameter("category"), 2);
//		int page = setDefaultValue(req.getParameter("page"), 1);
//		int amount = setDefaultValue(req.getParameter("amount"), 10);
//		
//		Criteria cri = Criteria.builder().page(page).amount(amount).category(cate).build();
		Criteria cri = new Criteria(req);
		System.out.println(cri);
//		System.out.println(service.list(cri));
		req.setAttribute("posts", service.list(cri));
		req.setAttribute("pageDto", new PageDto(cri, service.count(cri))); // 복습 요망
		req.getRequestDispatcher("/WEB-INF/jsp/post/list.jsp").forward(req,resp);
	}
	
//	private int setDefaultValue(String value, int def) {
//		return value == null || value.equals("") ? def : Integer.parseInt(value);
//	}
	
}
