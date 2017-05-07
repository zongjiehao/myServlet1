package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Users;
import com.service.UserService;

public class ManageUsers extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function goPage(){"
				+ "var pagenow=document.getElementById('pagenow');"
				+ "window.open('/myServlet1/ManageUsers?pagenow='+pagenow.value,'_self');}"
				+ "function conformdel(){" + "window.confirm('你确定要删除吗?');}");
		out.println("</script>");
		out.println("<img src='Images/1.jpg' /><hr>");
		out.println("<h3>管理用户</h3>");
		int pagenow = 1;// 当前页
		String spage = request.getParameter("pagenow");
		// java.lang.NumberFormatException: null
		// 当报这个错误的时候就要注意是否是空值
		if (spage != null) {
			pagenow = Integer.parseInt(spage);
		}
		int pagesize = 3;// 指定每页显示3条记录
		int pagecount = 1;// 该值是计算出来的
		UserService userService = new UserService();
		pagecount = userService.getPageCount(pagesize);
		ArrayList<Users> al = userService.getUserByPage(pagenow, pagesize);
		out.println("<table border='1' style='border-collapse:collapse' width='500px'>");
		out.println("<tr><th>用户id</th><th>用户名</th><th>邮件</th><th>级别</th><th>删除用户</th><th>修改用户</th></tr>");
		for (Users u : al) {
			out.println("<tr><td>"
					+ u.getId()
					+ "</td><td>"
					+ u.getUsername()
					+ "</td><td>"
					+ u.getEmail()
					+ "</td><td>"
					+ u.getGrade()
					+ "</td><td>"
					+ "<a onclick='return conformdel()' href='/myServlet1/delServlet?id="
					+ u.getId() + "'>删除用户</a>" + "</td><td>"
					+ "<a href='/myServlet1/delServlet?id=" + u.getId()
					+ "'>修改用户</a>" + "</td></tr>");
		}
		out.println("</table>");
		if (pagenow != 1) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow="
					+ (pagenow - 1) + "'><上一页></a>");
		} else {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + (pagenow)
					+ "'><上一页></a>");
		}
		for (int i = 1; i <= pagecount; i++) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + i + "'><"
					+ i + "></a>");
		}
		if (pagenow != pagecount) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow="
					+ (pagenow + 1) + "'><下一页></a>");
		} else {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + (pagenow)
					+ "'><下一页></a>");
		}
		out.println("&nbsp;&nbsp;&nbsp;当前页" + pagenow + "/总页数" + pagecount
				+ "<br/>");
		out.println("跳转到：" + "<input type='text'id='pagenow'/>"
				+ "<input type='button' onclick='goPage()'value='跳转'/>");
		out.println("<hr><img src='Images/3.GIF' />");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
