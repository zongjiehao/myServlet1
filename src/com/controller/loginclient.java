package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Users;
import com.service.UserService;

public class loginclient extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String passwd = request.getParameter("password");
		Users user = new Users();
		UserService userService = new UserService();
		user.setId(Integer.parseInt(id));
		user.setPassword(passwd);
		if (userService.checkUser(user)) {
			response.sendRedirect("/myServlet1/Mainframe");
		} else {
			out.println("µÇÂ¼Ê§°Ü£¡");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
