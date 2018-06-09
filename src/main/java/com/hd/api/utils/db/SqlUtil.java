package com.hd.api.utils.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Sql工具类
 * 
 * @author Hoda
 * @version 1.0
 * @since 2016-7-18
 */
public class SqlUtil {
	// 定义需要的变量
	// 创建数据库连接对象
	private static Connection conn = null;
	// 创建PreparedStatement对象
	private static PreparedStatement ps = null;
	// 创建结果集对象
	private static ResultSet rs = null;

	private static JDBCUtil jdbcUtil = JDBCUtil.getInstance();

	/**
	 * 该方法执行一个update/delete/insert语句
	 * 
	 * @param sql
	 *            带问号的格式，如：update table_name set column_name = ? where ..
	 * @param params
	 *            = {"...", "..."...}；
	 */
	public static int executeUpdate(String sql, Object[] params) {
		int affectedLine = 0;
		try {
			conn = jdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			// 给？赋值
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行语句
			affectedLine = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			jdbcUtil.free(conn, ps, rs);
		}
		return affectedLine;
	}

	/**
	 * 可以执行多个update、delete、insert语句（考虑事务）
	 */
	public static int executeUpdateByTrans(String[] sqls, String[][] params) {
		int affectedLine = 0;
		try {
			// 得到连接
			conn = jdbcUtil.getConnection();
			// 多个sql语句，考虑事务
			jdbcUtil.beginTransaction(conn);

			for (int i = 0; i < sqls.length; i++) {
				if (params[i] != null) {
					ps = conn.prepareStatement(sqls[i]);

					for (int j = 0; j < params[i].length; j++) {
						ps.setString(j + 1, params[i][j]);
					}
					affectedLine += ps.executeUpdate();
				}
			}
			// 提交事务
			jdbcUtil.commitTransaction(conn);
		} catch (SQLException e) {
			// 回滚事务
			jdbcUtil.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			jdbcUtil.free(conn, ps, rs);
		}
		return affectedLine;
	}

	/**
	 * SQL 查询将查询结果直接放入ResultSet中
	 */
	public static ResultSet executeQueryRS(String sql, Object[] params) {
		try {
			// 得到连接
			conn = jdbcUtil.getConnection();
			// 调用SQL
			ps = conn.prepareStatement(sql);
			// 参数赋值
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.free(conn, ps, rs);
		}

		return rs;
	}
	
	/**
	 * 判断是否存在记录
	 */
	public static boolean exist(String sql, Object[] params) {
		boolean result = false;
		try {			
			conn = jdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.free(conn, ps, rs);
		}
		return result;		
	}

	/**
	 * 查询单条记录
	 */
	public static Map<String, Object> executeQuerySingle(String sql,
			Object[] params) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			conn = jdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();// 返回查询结果
			ResultSetMetaData metaData = rs.getMetaData();
			int col_len = metaData.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < col_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = rs.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.free(conn, ps, rs);
		}
		return map;
	}

	/**
	 * 查询多条记录
	 */
	public static List<Map<String, Object>> executeQuery(String sql,
			Object[] params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = jdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			ResultSet resultSet = ps.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int cols_len = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = resultSet.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.free(conn, ps, rs);
		}
		return list;
	}
}
