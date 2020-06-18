package com.cos.apple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.apple.action.Action;
import com.cos.apple.action.post.PostListAction;

// http://localhost:8000/apple/post
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request utf-8 세팅 = web.xml 필터 등록함.
		// 2. response utf-8 세팅
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//key=value
		String cmd = request.getParameter("cmd");
		Action action = router(cmd);
		action.execute(request, response);
	}
	
	private Action router(String cmd) {
		// http://localhost:8000/apple/post?cmd=list
		if(cmd.equals("list")) {
			return new PostListAction();
		}
		return null;
	}
	
}

