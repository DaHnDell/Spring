package com.kcanmin.member_post.servlet.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Commons;

@WebServlet("/download")
public class Download extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 수집
		String uuid = req.getParameter("uuid");
		String origin = req.getParameter("origin");
		String path = req.getParameter("path");
		
		if(uuid == null || origin == null || path == null) {
			Commons.printMsg("SYSTEM :: Invalid Approach!", null, resp);
			return;
		}
		
		File file = new File(Commons.UPLOAD_PATH, path);
		file = new File(file, uuid);
		
		if(!file.exists()) {
			Commons.printMsg("SYSTEM :: Invalid Approach!", null, resp);
			return;
		}
		// 파일 실제 이름 처리
		String fileName = new String(origin.getBytes("utf-8"), "iso-8859-1");
		// 응답 처리
		resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", String.format("attachment; filename=%s", fileName));
		
		
		FileInputStream fis = new FileInputStream(file);
		byte[] bytes = fis.readAllBytes();
		resp.getOutputStream().write(bytes);
		fis.close();
	}

	
}
