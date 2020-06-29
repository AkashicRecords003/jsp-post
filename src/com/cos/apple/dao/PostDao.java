package com.cos.apple.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.apple.db.DBConn;
import com.cos.apple.dto.PostResponseDto;
import com.cos.apple.model.Post;


public class PostDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int 글삭제(int id) {
		try {
			String sql = "DELETE FROM post WHERE id = ?";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	public Post 글가져오기(int id) {
		try {
			String sql = "SELECT * FROM post WHERE id=? ";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Post post = Post.builder()
						.id(rs.getInt("id"))
						.memberId(rs.getInt("memberId"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.createDate(rs.getTimestamp("createDate"))
						.build();

				return post;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int 글수정(int id, String title, String content) {
		try {
			String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, id);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public PostResponseDto 상세보기(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT p.id, p.memberId, p.title, p.content, p.createDate, m.username ");
		sb.append("FROM post p INNER JOIN member m ");
		sb.append("ON p.memberId = m.id ");
		sb.append("WHERE p.id = ?");
		
		final String sql = sb.toString();
		PostResponseDto postDto = null;

		try {

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				postDto = new PostResponseDto();
				Post post = Post.builder()
						.id(rs.getInt(1))
						.memberId(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.createDate(rs.getTimestamp(5))
						.build();
				postDto.setPost(post);
				postDto.setUsername(rs.getString(6));
			}
			System.out.println("postDto:" + postDto.getUsername());
			return postDto;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Post> 글목록() {
		try {
			String sql = "SELECT * FROM post ORDER BY id DESC";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Post> posts = new ArrayList<>();
			while(rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setMemberId(rs.getInt("memberId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setCreateDate(rs.getTimestamp("createDate"));
				posts.add(post);
			}
			return posts;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int 글쓰기(int memberId, String title, String content) {
		try {
			String sql = "INSERT INTO post VALUES(post_seq.nextval, ?,?,?, sysdate)";
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

