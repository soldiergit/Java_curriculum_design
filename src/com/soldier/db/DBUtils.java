package com.soldier.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库操作工具类
 * @author   用到静态代码块的知识点，只会在这个类加载的时候执行一次
 *
 */

public class DBUtils {

	//数据库连接地址
	public static String URL;
	//用户名
	public static String USERNAME;
	//密码
	public static String PASSWORD;
	//MySQL的驱动
	public static String DRIVER;

	private static ResourceBundle rb = ResourceBundle.getBundle("com.soldier.db.db-config");

	//使用静态块加载驱动程序，因为只执行一次
	static {
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("password");
		DRIVER = rb.getString("driver");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载数据库驱动程序失败！");
		}
	}

	//定义一个获取数据库连接的方法
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取数据库连接失败！");
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param state
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement state,Connection conn) {
		try {
			if(rs != null) rs.close();
			if(state != null) state.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
