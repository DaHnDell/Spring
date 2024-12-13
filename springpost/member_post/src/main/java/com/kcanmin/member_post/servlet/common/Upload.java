// package com.kcanmin.member_post.servlet.common;

// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.apache.commons.fileupload.FileItem;
// import org.apache.commons.fileupload.disk.DiskFileItemFactory;
// import org.apache.commons.fileupload.servlet.ServletFileUpload;

// import com.google.gson.Gson;

// import com.kcanmin.member_post.utils.Commons;
// import com.kcanmin.member_post.vo.Attach;

// @WebServlet("/upload")
// public class Upload extends HttpServlet{
// 	@Override
// 	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 		DiskFileItemFactory factory = new DiskFileItemFactory();
// 		factory.setSizeThreshold(1024*1024);
// 		factory.setRepository(new File(Commons.UPLOAD_PATH, "tmp"));
// //		factory.setRepository(new File(Commons.UPLOAD_PATH + File.separator +  "tmp"));
// 		// 파일 이름 생성 규칙을 알아야 함. File.seperator = 상수에 지정되는 값이 실행 시에 지정됨. 비초기화 상수.
// 		ServletFileUpload upload = new ServletFileUpload(factory);
// 		List<Attach> attachs = new ArrayList<>();
// 		try {
// 			List<FileItem> items = upload.parseRequest(req);
// 			for(FileItem item : items) {
// //			items.forEach(item->{
// //				item.getFieldName() files
// 				System.out.println(item.getName());
// 				System.out.println(item.getSize());
				
// 				String origin = item.getName();
// 				int dotIdx = origin.lastIndexOf(".");
// 				String ext = "";
// 				if(dotIdx != -1) {
// 					origin.substring(dotIdx);
// 				}
// 				String uuid = UUID.randomUUID().toString();
// 				String realName = uuid + ext;
// 				String path = getTodayStr();
// 				File parentPath = new File(Commons.UPLOAD_PATH, path);
// 				if(!parentPath.exists()) {
// 					parentPath.mkdirs();
// 					// s 안붙은 애들은 마지막 하나만 만들어주는 메서드라서 무조건 s 붙은걸로 써야 함.
// 				}
// 				item.write(new File(parentPath, realName));
// 				attachs.add(Attach.builder().uuid(realName).path(path).origin(origin).build());
// 			}
// 			resp.setContentType("application/json; charset-utf-8");
// 			resp.getWriter().print(new Gson().toJson(attachs));
			
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
		
// 	}
	
// 	public String getTodayStr() {
// 		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
// 	}
	

// }


