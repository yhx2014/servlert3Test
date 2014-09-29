/*  
 * @(#) BDUtil.java Create on 2014-9-22 下午8:56:20   
 *   
 * Copyright 2014 by pztx.   
 */


package wz.test.std.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @BDUtil.java
 * @created at 2014-9-22 下午8:56:20 by zhanghl
 *
 * @desc
 *
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class DBUtil {
	private static String password = null;
	private static String user = null;
	private static String url = null;

	private static class INSTALL_HANDER {
		private static  DBUtil INSTALL = new DBUtil();
	}
	
	private DBUtil() {
		url = JdbcPropertiesUtil.getValue("jdbc.url");
		user = JdbcPropertiesUtil.getValue("jdbc.uname");
		password = JdbcPropertiesUtil.getValue("jdbc.pwd");
		String driver = JdbcPropertiesUtil.getValue("jdbc.driver");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static DBUtil getInstall(){
		return INSTALL_HANDER.INSTALL;
	}
	
	public static void main(String[] args) {
		DBUtil db = getInstall();
		Connection conn= db.getConnection();
		System.out.println(conn);
		System.out.println(url);
	}
}
