/*  
 * @(#) H2Test.java Create on 2014-9-24 上午10:00:32   
 *   
 * Copyright 2014 by yhx.   
 */

package wz.test.std.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;
import org.h2.tools.Server;

/**
 * @H2Test.java
 * @created at 2014-9-24 上午10:00:32 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class H2Utils implements LogEable {
	private Server server;
	private String port = "9094";
	private String dbDir = "./h2db/sample";
	private String user = "zhanghl";
	private String password = "zhanghl";

	public H2Utils() {
		port = JdbcPropertiesUtil.getValue("jdbc.h2.port");
		port = StringUtils.defaultIfBlank(port, "9094");
		user = JdbcPropertiesUtil.getValue("jdbc.uname");
		password = JdbcPropertiesUtil.getValue("jdbc.pwd");
	}

	public void startServer() {
		try {
			logger.info("正在启动h2...");
			server = Server.createTcpServer(new String[] { "-tcpPort", port })
					.start();
		} catch (SQLException e) {
			logger.error("启动h2出错：" + e.toString());
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {

			public void run() {
				logger.info("jvm关闭 停止h2 数据库");
				stopServer();
			}
		});
	}

 

	public void stopServer() {
		if (server != null) {
			logger.info("正在关闭h2...");
			server.stop();
			logger.info("关闭成功.");
		}
	}

	public void useH2() {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir,
					user, password);
			Statement stat = conn.createStatement();
			// insert data
			stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
			stat.execute("INSERT INTO TEST VALUES('Hello World')");

			// studentID 学号(身份证号) 字符型 18位 主键
			// studentName 姓名 字符 12
			// studentName 年龄 数值
			// studentBirthdate 生日
			// studentPicture 学生照片 图片
			String sql = "CREATE TABLE std(studentID varchar(18),studentName varchar(30),studentAge int,studentBirthdate DATE,studentPicture BLOB)";
			
			stat.execute(sql);
			// use data
			ResultSet result = stat.executeQuery("select name from test ");
			int i = 1;
			while (result.next()) {
				System.out.println(i++ + ":" + result.getString("name"));
			}
			result.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		H2Utils h2 = new H2Utils();
		h2.startServer();
		h2.useH2();
		h2.stopServer();
		System.out.println("==END==");
	}
}
