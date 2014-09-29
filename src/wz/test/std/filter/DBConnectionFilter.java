/*  
 * @(#) DBConnectionFilter.java Create on 2014-9-22 下午8:37:04   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.exception.ExceptionUtils;

import wz.test.std.util.DruiDataSourceFactory;
import wz.test.std.util.LogEable;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @DBConnectionFilter.java
 * @created at 2014-9-22 下午8:37:04 by zhanghl
 * 
 * @desc conection filter
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
@WebFilter("/std/*")
public class DBConnectionFilter implements Filter,LogEable {
//	private DBUtil dbUtil;
	private DruidDataSource druid;
	private static ThreadLocal<Connection> conectionThreadLocal;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
//		Connection conn = dbUtil.getConnection();
		try {
			Connection conn = druid.getConnection();
			conectionThreadLocal.set(conn);
			
			arg1.setContentType("text/html;charset=utf-8");
			arg0.setCharacterEncoding("utf-8");
			arg2.doFilter(arg0, arg1);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}finally{
			Connection conn = conectionThreadLocal.get();
			druid.discardConnection(conn);		
		}
	}

	public static Connection getConnection(){
		return conectionThreadLocal.get();
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.debug(getClass() + " filter init......");
//		dbUtil = DBUtil.getInstall();
		druid = new DruiDataSourceFactory().getDruidDataSource();
		conectionThreadLocal = new ThreadLocal<>();
	}
}
