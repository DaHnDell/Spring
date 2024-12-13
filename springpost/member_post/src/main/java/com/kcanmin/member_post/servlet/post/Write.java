package com.kcanmin.member_post.servlet.post;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Criteria;
import service.PostService;
import service.PostServiceImpl;
import vo.Attach;
import vo.Post;

@WebServlet("/post/write")
public class Write extends HttpServlet{
	PostService postService = new PostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = new Criteria(req);
		if(req.getSession().getAttribute("member") == null){
			String cp = req.getContextPath();
			resp.sendRedirect(cp + "/signin?url=" + 
													URLEncoder.encode(cp + "/post/write" + criteria.getQs2(), "utf-8"));
			return;
		}
		req.setAttribute("criteria", criteria);
		req.getRequestDispatcher("/WEB-INF/jsp/post/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = new Criteria(req);
		String title = req.getParameter("title"); 
		String content = req.getParameter("content"); 
		String writer  = req.getParameter("writer");

		List<Attach> attachs = new ArrayList<>();
		
		// 첨부파일 정보 수집부
		String[] uuids = req.getParameterValues("uuid");
		String[] origins = req.getParameterValues("origin");
		String[] images = req.getParameterValues("image");
		String[] paths = req.getParameterValues("path");
		
		if(uuids !=null) {
			
			for(int i = 0; i < uuids.length; i++) {
				attachs.add(Attach.builder()
						.uuid(uuids[i])
						.origin(origins[i])
						.image(images[i].equals("true"))
						.path(paths[i])
						.build());
			}
			
		}
		
		
		postService.write(Post.builder().title(title).content(content).writer(writer).cno(criteria.getCategory()).attachs(attachs).build());
		resp.sendRedirect("list?"+criteria.getQs2());
	}

	
	
}
