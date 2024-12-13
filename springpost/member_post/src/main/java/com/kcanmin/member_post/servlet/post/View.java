package com.kcanmin.member_post.servlet.post;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Criteria;
import service.PostService;
import service.PostServiceImpl;
import utils.Commons;
import vo.Post;

@WebServlet("/post/view")
public class View extends HttpServlet{

	private PostService service = new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = new Criteria(req);
		
		
		String pnoString = req.getParameter("pno");
		if(pnoString == null) {
			Commons.printMsg("SYSTEM :: ERR / INVALID APPROACH", "list", resp);
			return;
		}
		Long pno = Long.valueOf(pnoString);
		req.setAttribute("post", service.view(pno));
		req.setAttribute("criteria", criteria); // 모델 어트리뷰트
		req.getRequestDispatcher("/WEB-INF/jsp/post/view.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
