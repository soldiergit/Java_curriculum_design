package com.soldier.db;

import com.soldier.handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Templete--模板
 * 对dao的改进，因为增删改中只有sql语句和其所要赋值的占位符个数不同，其他均相同
 *
 *     Object是所有类型(int,String..)的父类
 *       Object...args：表示参数未知，可能多个，因为sql语句中的占位符不同
 */
public class JdbcTemplete {

	/**
	 * 实现增删改
	 * @param sql
	 * @param args
	 */
	public void updete(String sql,Object...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtils.getConnection();
			//预编译sql语句
			ps = conn.prepareStatement(sql);
			//给改sql语句中的占位符赋值
			if(args != null) {
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i+1, args[i]);//i+1是因为位置从1开始
				}
			}
			//执行sql语句
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, ps, conn);
		}
	}

	/**
	 * 查询方法的实现
	 * @param sql
	 * @param handler
	 * @param args
	 * @return                    ResultSetHandler接口     **其实接口也是一个类**
	 */
	public Object query(String sql, ResultSetHandler handler, Object...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			//预编译
			ps = conn.prepareStatement(sql);
			//赋值
			if(args != null){
				for (int i = 0; i < args.length; i++) {
					//用setObject是因为object是所有类型的父类
					ps.setObject(i+1, args[i]);
				}
			}
			//执行
			rs = ps.executeQuery();
			Object result = handler.doHandler(rs);
			return  result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtils.close(rs, ps, conn);
		}
	}

}






