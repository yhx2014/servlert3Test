/*  
 * @(#) StdListServlet.java Create on 2014-9-22 下午9:14:14   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @StdCheckExistServlet.java
 * @created at 2014-9-22 下午9:14:14 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
@WebServlet(urlPatterns = "/std/checkExist")
@MultipartConfig 
public class StdCheckExistServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Map<String, String> rq = requestToMap(req);

			// 检查是否已经存在
			boolean boo = this.service.checkExist(rq.get("studentID"));
			
			PrintWriter writer = resp.getWriter();
			writer.write(!boo + "");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
