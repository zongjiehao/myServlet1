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
				+ "function conformdel(){" + "window.confirm('��ȷ��Ҫɾ����?');}");
		out.println("</script>");
		out.println("<img src='Images/1.jpg' /><hr>");
		out.println("<h3>�����û�</h3>");
		int pagenow = 1;// ��ǰҳ
		String spage = request.getParameter("pagenow");
		// java.lang.NumberFormatException: null
		// ������������ʱ���Ҫע���Ƿ��ǿ�ֵ
		if (spage != null) {
			pagenow = Integer.parseInt(spage);
		}
		int pagesize = 3;// ָ��ÿҳ��ʾ3����¼
		int pagecount = 1;// ��ֵ�Ǽ��������
		UserService userService = new UserService();
		pagecount = userService.getPageCount(pagesize);
		ArrayList<Users> al = userService.getUserByPage(pagenow, pagesize);
		out.println("<table border='1' style='border-collapse:collapse' width='500px'>");
		out.println("<tr><th>�û�id</th><th>�û���</th><th>�ʼ�</th><th>����</th><th>ɾ���û�</th><th>�޸��û�</th></tr>");
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
					+ u.getId() + "'>ɾ���û�</a>" + "</td><td>"
					+ "<a href='/myServlet1/delServlet?id=" + u.getId()
					+ "'>�޸��û�</a>" + "</td></tr>");
		}
		out.println("</table>");
		if (pagenow != 1) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow="
					+ (pagenow - 1) + "'><��һҳ></a>");
		} else {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + (pagenow)
					+ "'><��һҳ></a>");
		}
		for (int i = 1; i <= pagecount; i++) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + i + "'><"
					+ i + "></a>");
		}
		if (pagenow != pagecount) {
			out.println("<a href='/myServlet1/ManageUsers?pagenow="
					+ (pagenow + 1) + "'><��һҳ></a>");
		} else {
			out.println("<a href='/myServlet1/ManageUsers?pagenow=" + (pagenow)
					+ "'><��һҳ></a>");
		}
		out.println("&nbsp;&nbsp;&nbsp;��ǰҳ" + pagenow + "/��ҳ��" + pagecount
				+ "<br/>");
		out.println("��ת����" + "<input type='text'id='pagenow'/>"
				+ "<input type='button' onclick='goPage()'value='��ת'/>");
		out.println("<hr><img src='Images/3.GIF' />");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
