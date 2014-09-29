/*  
 * @(#) DBInitServlet.java Create on 2014-9-24 下午6:09:11   
 *   
 * Copyright 2014 by yhx.   
 */

package wz.test.std.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @DBInitServlet.java
 * @created at 2014-9-24 下午6:09:11 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class DruiDataSourceFactory implements LogEable {


	public void init(DruidDataSource dataSource) {
		if (dataSource != null) {
			try {
				Connection conn = dataSource.getConnection();
				String createTablesql = "CREATE TABLE std(studentID varchar(18),studentName varchar(30),studentAge int,studentBirthdate DATE,studentPicture BLOB)";

				DatabaseMetaData dbmd = conn.getMetaData();
				String tableName = "STD";
				ResultSet set = dbmd.getTables(null, null, tableName, new String[] {"TABLE"}); // 获取查找结果
				boolean result = false;
				while (set.next()) { // 如果查找结果不为空，则说明存在该表
					result = true; // 将返回结果置为true
				}
				
				if(result==false){
					logger.info("表" +tableName  + " 不存在，新创建");
					conn.prepareStatement(createTablesql).executeUpdate();
					
					logger.debug("添加测试数据.........");
					String name = "0-test-";
					String insertSql = "insert INTO  " + tableName + " (studentID,studentName,studentAge,studentBirthdate) values (?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					for (int i = 0; i < 10; i++) {
						ps.setString(1, name + (i+1));
						ps.setString(2, name +"name-"+ (i+1));
						ps.setInt(3, i+10);
						ps.setDate(4, new java.sql.Date(new Date().getTime()));
						ps.addBatch();
					}
					ps.executeBatch();
				}
				dataSource.discardConnection(conn);
			} catch (SQLException e) {
				logger.error("init", e);
			}
		}
	}

	public DruidDataSource getDruidDataSource() {
		String url = "jdbc:mysql://118.244.211.26:3306/vcard?characterEncoding=UTF-8";
		String username = "root";
		String password = "root122333";
		String driverClass = "com.mysql.jdbc.Driver";

		url = JdbcPropertiesUtil.getValue("jdbc.url");
		username = JdbcPropertiesUtil.getValue("jdbc.uname");
		password = JdbcPropertiesUtil.getValue("jdbc.pwd");
		driverClass = JdbcPropertiesUtil.getValue("jdbc.driver");
		logger.debug("数据库连接信息");
		logger.debug("url:" + url);
		logger.debug("username:" + username);
		logger.debug("password:" + password);
		logger.debug("driverClass:" + driverClass);

		int maxActive = 20;
		int initialSize = 1;
		int maxWait = 60000;
		int minIdle = 1;

		String validationQuery = "SELECT 1";

		DruidDataSource druidDataSource = new DruidDataSource();

		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setDriverClassName(driverClass);
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setTimeBetweenEvictionRunsMillis(3000);
		druidDataSource.setMinEvictableIdleTimeMillis(300000);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setValidationQuery(validationQuery);

		init(druidDataSource);
		return druidDataSource;
	}

	public static void main(String[] args) {
		try {
			Connection conn = new DruiDataSourceFactory().getDruidDataSource()
					.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
