/*  
 * @(#) StdListServlet.java Create on 2014-9-22 下午9:14:14   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.serlvet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @StdImageServlet.java
 * @created at 2014-9-22 下午9:14:14 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
@WebServlet(urlPatterns = "/std/image")
public class StdImageServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Map<String, String> rq = requestToMap(req);
			OutputStream out = this.service.getImageById(rq.get("id"));

			ByteArrayOutputStream o = (ByteArrayOutputStream) out;
			if(o!=null){
				ServletOutputStream outputStream = resp.getOutputStream();
				o.writeTo(outputStream);
				outputStream.flush();
				outputStream.close();
			}
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
