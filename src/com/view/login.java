package com.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html,charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='Images/1.jpg' /><hr>");
		out.println("<h1>用户登录</h1>");
		out.println("<form action='/myServlet1/loginclient' method='post'>");
		out.println("用名id:<input type='text' name='id'/><br/>");
		out.println("密&nbsp码:<input type='password' name='password'/><br/>");
		out.println("<input type='submit' value='提交'/>");
		out.println("</form>");
		out.println("<hr><img src='Images/3.GIF' />");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}
}
