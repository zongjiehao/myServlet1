package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.domain.Users;
import com.utils.SqlHelper;

public class UserService {
	// 验证用户合法性
	public boolean checkUser(Users user) {
		boolean b = false;
		String sql = "select * from users where id=? and passwd=?";
		String para[] = { user.getId() + "", user.getPassword() };
		ResultSet rs = SqlHelper.excuteQuery(sql, para);
		try {
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return b;
	}

	// 获取分页页数
	public int getPageCount(int pagesize) {
		String sql = "select count(*) from users";
		ResultSet rs = SqlHelper.excuteQuery(sql, null);
		int rowcount = 0;
		try {
			rs.next();
			rowcount = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}

		return (rowcount - 1) / pagesize + 1;
	}

	// 分页算法
	public ArrayList getUserByPage(int pagenow, int pagesize) {
		ArrayList<Users> al = new ArrayList<Users>();
		String sql = "select * from users limit ?,?";
		int para[] = { (pagenow * pagesize - 3), pagesize };
		ResultSet rs = SqlHelper.intExcuteQuery(sql, para);
		try {
			while (rs.next()) {

				Users user = new Users();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setGrade(rs.getInt(4));
				al.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return al;
	}

	// 删除用户
	public boolean delUser(String id) {
		boolean b = true;
		String sql = "delete from users where id=?";
		String para[] = { id };
		try {
			SqlHelper.ExcuteUpdate(sql, para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}
		return b;
	}
}
