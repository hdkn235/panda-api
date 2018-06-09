package com.hd.api.utils.db;

import com.hd.utils.common.PropsUtil;

import java.sql.*;

/**
 * JDBC工具类
 * 
 * @author Hoda
 * @version 1.0
 * @since 2016-7-18
 */
public class JDBCUtil {

	private final static String CONFIG_FILE_NAME = "config.properties";

	// 连接数据库的参数
	// 连接字符串 jdbc:sqlserver://localhost\\MSSQLSERVER;database=ktjn;
	private static String url = null;
	// 数据库驱动类名称 com.microsoft.sqlserver.jdbc.SQLServerDriver
	private static String driver = null;
	// 用户名
	private static String user = null;
	// 密码
	private static String password = null;

	private JDBCUtil() {

	}

	private static JDBCUtil instance = null;

	/**
	 * 单例模式，获取唯一实例
	 * 
	 * @return
	 */
	public static JDBCUtil getInstance() {
		if (instance == null) {
			synchronized (JDBCUtil.class) {
				if (instance == null) {
					instance = new JDBCUtil();
				}
			}
		}

		return instance;
	}

	// 注册驱动
	static {
		try {
			url = PropsUtil.getString("db_url", CONFIG_FILE_NAME);
			user = PropsUtil.getString("db_user", CONFIG_FILE_NAME);
			driver = PropsUtil.getString("db_driver", CONFIG_FILE_NAME);
			password = PropsUtil.getString("db_password", CONFIG_FILE_NAME);
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 该方法获得连接
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
		// return
		// DriverManager.getConnection("jdbc:sqlserver://localhost\\MSSQLSERVER;database=ktjn;user=sa;password=123456;");
	}

	/**
	 * 开启事务
	 */
	public void beginTransaction(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交事务
	 */
	public void commitTransaction(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 事务回滚
	 */
	public void rollbackTransaction(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重置事务开启状态
	 */
	public void resetConnection(Connection conn) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				} else {
					conn.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 */
	public void free(Connection conn, Statement st, ResultSet rs) {
		// 关闭结果集资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭Statement对象
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭连接
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
