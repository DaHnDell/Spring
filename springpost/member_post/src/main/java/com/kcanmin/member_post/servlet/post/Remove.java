package com.kcanmin.member_post.servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Criteria;
import service.PostService;
import service.PostServiceImpl;
import utils.Commons;
import vo.Member;

@WebServlet("/post/remove")
public class Remove extends HttpServlet {

	private PostService service = new PostServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 오류 메세지, 이동할 위치. 
		// 응답의 컨텐트타입, 어떤 메세지를, 어디로 보내느냐에 관한 3가지의 파라미터.
		String pnoStr = req.getParameter("pno");
		Object memberObj = req.getSession().getAttribute("member");
		Criteria criteria = new Criteria(req);
		String redirectURL = "list?" + criteria.getQs2();
		
		if(pnoStr == null || memberObj == null) {
			Commons.printMsg("SYSTEM :: ERR / INVALID APPROACH", redirectURL, resp);
			return;
		}
		
		Long pno = Long.valueOf(pnoStr);
		Member m = (Member)memberObj;
		
		if(!m.getId().equals(service.findBy(pno).getWriter())) {
			Commons.printMsg("SYSTEM :: ERR / POST CAN BE ONLY REMOVED BY WRITER OF IT\'S OWN", redirectURL, resp);
			return;
		}
		
		// 수정 = 조회 + 등록
		service.remove(pno);
		resp.sendRedirect(redirectURL);
	}


}
