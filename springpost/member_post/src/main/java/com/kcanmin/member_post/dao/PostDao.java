package com.kcanmin.member_post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import utils.DBConn;
import vo.Post;

@Slf4j
public class PostDao {
	public int insert(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into tbl_post(title, writer, content, cno) values(?, ?, ?, ?)";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setString(idx++, post.getTitle()); 
			pstmt.setString(idx++, post.getWriter()); 
			pstmt.setString(idx++, post.getContent()); 
			pstmt.setInt(idx++, post.getCno()); 
			return pstmt.executeUpdate();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}
	
	public Post selectOne(Long pno) {
		Post post = null;
		String sql = "select pno, title, writer, content, view_cnt, regdate, updatedate from tbl_post where pno = ?";
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, pno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int idx = 1;
				post = Post
					.builder()
					.pno(rs.getLong(idx++))
					.title(rs.getString(idx++))
					.writer(rs.getString(idx++))
					.content(rs.getString(idx++))
					.viewCnt(rs.getLong(idx++))
					.regDate(rs.getDate(idx++))
					.updateDate(rs.getDate(idx++))
					.build();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public int getCount(Criteria cri){
		String sql = "select count(*) as cnt\r\n"
				+ "from tbl_post\r\n"
				+ "where cno = ?;";
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cri.getCategory());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Post> selectList(Criteria cri){
		List<Post> posts = new ArrayList<Post>();
		String sql = "select pno, title, writer, view_cnt, regdate, cno \r\n"
				+ "from tbl_post\r\n"
				+ "where cno = ?\r\n"
				+ "order by 1 desc \r\n"
				+ "limit ? offset ?";
//		System.out.println(sql);
//		System.out.println(cri.getOffSet());
		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cri.getCategory());
			pstmt.setInt(2, cri.getAmount());
			pstmt.setInt(3, cri.getOffset());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = 1;
				Post post = Post
						.builder()
						.pno(rs.getLong(idx++))
						.title(rs.getString(idx++))
						.writer(rs.getString(idx++))
						.viewCnt(rs.getLong(idx++))
						.regDate(rs.getDate(idx++))
						.cno(rs.getInt(idx++))
						.build();
				posts.add(post);
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}
	
	
	public int update(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update tbl_post set title = ?, content = ?, updatedate = now() where pno = ?";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			
			pstmt.setString(idx++, post.getTitle()); 
			pstmt.setString(idx++, post.getContent()); 
			pstmt.setLong(idx++, post.getPno()); 
			return pstmt.executeUpdate();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}

	public int increaseViewCount(Long pno) {
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		try {
			String sql = "update tbl_post set view_cnt = view_cnt + 1 where pno = ?";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, pno); 
			return pstmt.executeUpdate();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}
	
	
	public int delete(Long pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from tbl_post where pno = ?";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, pno);
			return pstmt.executeUpdate();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		PostDao dao = new PostDao();
//		Criteria cri = new Criteria(2, 10, 2);
		
//		dao.selectList(cri).forEach(System.out::println);
//		System.out.println("=======count=======");
//		System.out.println(dao.getCount(cri));
		
		
		
//		for(int i = 0; i < 10; i++) {
//			int j = dao.insert(Post.builder().writer("abcd").title("title + " + (i + 1)).content("Content " + i + 1).build());
//			System.out.println(i + " ::: " + j);
//		}
//		System.out.println();
//		dao.delete(6L);
//		System.out.println(dao.selectOne(6L));
//		int result = dao.insert(Post.builder().title("test@03").writer("rlagksals").content("contentTest.03").build());
//		System.out.println(result);
		
		
//		Post sel1Post = dao.selectOne(4L);
//		System.out.println(sel1Post);
//		int result2 = dao.update(sel1Post.builder().title("ctitle@").content("contentTest.03c").pno(sel1Post.getPno()).build()); 
//		System.out.println(result2);
//		
//		// 강사님 코드
//		Post post = dao.selectOne(10L);
//		System.out.println(post);
//		post = Post.builder().pno(post.getPno()).title("modTitle").content("modContent").build();
//		dao.update(post);
//		post = dao.selectOne(10L);
//		System.out.println(post);
		
	}
}
