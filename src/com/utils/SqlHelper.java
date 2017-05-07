package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SqlHelper {
	private static String driver = "";
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static Connection ct = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	// 读取配置文件
	private static Properties pp = null;
	private static InputStream is = null;
	static {
		try {
			pp = new Properties();
			// 当使用java web时候，读取配置文件需要用类加载器【类加载器读取资源，默认的主目录是src】
			is = SqlHelper.class.getClassLoader().getResourceAsStream(
					"dbinfo.properties");
			pp.load(is);
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			password = pp.getProperty("password");
			driver = pp.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		is = null;
	}

	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	public static ResultSet excuteQuery(String sql, String[] para) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (para != null && !para.equals("")) {
				for (int i = 0; i < para.length; i++) {
					ps.setString(i + 1, para[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet intExcuteQuery(String sql, int[] para) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (para != null && !para.equals("")) {
				for (int i = 0; i < para.length; i++) {
					ps.setInt(i + 1, para[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// 执行更新/删除等操作
	public static void ExcuteUpdate(String sql, String[] para) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (para != null && !para.equals("")) {
				for (int i = 0; i < para.length; i++) {
					ps.setString(i + 1, para[i]);
				}
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

}
