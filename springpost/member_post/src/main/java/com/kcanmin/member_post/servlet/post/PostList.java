// package com.kcanmin.member_post.servlet.post;

// import java.io.IOException;
// import java.util.List;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import com.kcanmin.member_post.dto.Criteria;
// import com.kcanmin.member_post.dto.PageDto;
// import com.kcanmin.member_post.service.PostService;
// import com.kcanmin.member_post.service.PostServiceImpl;
// import com.kcanmin.member_post.vo.Post;

// @WebServlet("/post/list")
// public class PostList extends HttpServlet{

// 	private PostService service = new PostServiceImpl();
	
// 	@Override
// 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		// param getter
// //		int cate = setDefaultValue(req.getParameter("category"), 2);
// //		int page = setDefaultValue(req.getParameter("page"), 1);
// //		int amount = setDefaultValue(req.getParameter("amount"), 10);
// //		
// //		Criteria cri = Criteria.builder().page(page).amount(amount).category(cate).build();
// 		Criteria cri = new Criteria(req);
// 		System.out.println(cri);
// //		System.out.println(service.list(cri));
// 		req.setAttribute("posts", service.list(cri));
// 		req.setAttribute("pageDto", new PageDto(cri, service.count(cri))); // 복습 요망
// 		req.getRequestDispatcher("/WEB-INF/jsp/post/list.jsp").forward(req,resp);
// 	}
	
// //	private int setDefaultValue(String value, int def) {
// //		return value == null || value.equals("") ? def : Integer.parseInt(value);
// //	}
	
// }
