// package com.kcanmin.member_post.servlet.post;

// import java.io.IOException;
// import java.net.URLEncoder;
// import java.util.ArrayList;
// import java.util.List;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import com.kcanmin.member_post.dto.Criteria;
// import com.kcanmin.member_post.service.PostService;
// import com.kcanmin.member_post.service.PostServiceImpl;
// import com.kcanmin.member_post.vo.Attach;
// import com.kcanmin.member_post.vo.Post;

// @WebServlet("/post/write")
// public class Write extends HttpServlet{
// 	// PostService postService = new PostServiceImpl();

// 	@Override
// 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		Criteria criteria = new Criteria(req);
// 		if(req.getSession().getAttribute("member") == null){
// 			String cp = req.getContextPath();
// 			resp.sendRedirect(cp + "/signin?url=" + 
// 													URLEncoder.encode(cp + "/post/write" + criteria.getQs2(), "utf-8"));
// 			return;
// 		}
// 		req.setAttribute("criteria", criteria);
// 		req.getRequestDispatcher("/WEB-INF/jsp/post/write.jsp").forward(req, resp);
// 	}

// 	@Override
// 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		Criteria criteria = new Criteria(req);
// 		String title = req.getParameter("title"); 
// 		String content = req.getParameter("content"); 
// 		String writer  = req.getParameter("writer");

// 		List<Attach> attachs = new ArrayList<>();
		
// 		// 첨부파일 정보 수집부
// 		String[] uuids = req.getParameterValues("uuid");
// 		String[] origins = req.getParameterValues("origin");
// 		String[] images = req.getParameterValues("image");
// 		String[] paths = req.getParameterValues("path");
		
// 		if(uuids !=null) {
			
// 			for(int i = 0; i < uuids.length; i++) {
// 				attachs.add(Attach.builder()
// 						.uuid(uuids[i])
// 						.origin(origins[i])
// 						.image(images[i].equals("true"))
// 						.path(paths[i])
// 						.build());
// 			}
			
// 		}
		
		
// 		postService.write(Post.builder().title(title).content(content).writer(writer).cno(criteria.getCategory()).attachs(attachs).build());
// 		resp.sendRedirect("list?"+criteria.getQs2());
// 	}

	
	
// }
