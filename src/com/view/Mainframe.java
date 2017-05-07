package com.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Mainframe extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='Images/1.jpg' /><hr>");
		out.println("<h1>欢迎来到用户管理系统！</h1>");
		out.println("<h2>请选择进行的操作</h3>");
		out.println("<a href='/myServlet1/ManageUsers'>管理用户</a><br/>");
		out.println("<a>添加用户</a><br/>");
		out.println("<a>查找用户</a><br/>");
		out.println("<a>退出系统</a><br/>");
		out.println("<hr><img src='Images/3.GIF' />");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
