/*  
 * @(#) StdListServlet.java Create on 2014-9-22 下午9:14:14   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.serlvet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @StdListServlet.java
 * @created at 2014-9-22 下午9:14:14 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
@WebServlet(urlPatterns = "/std/save")
@MultipartConfig 
public class StdSaveServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "list";
		try {
			Map<String, String> rq = requestToMap(req);

			String type = rq.get("type");
			
			Part part = req.getPart("studentPicture");
			int model = this.service.save(rq, part);
			url += "?type=" + type + "&result=" + (model==1);
			url += "&q_name=" + rq.get("studentID");
			redirect(resp, url);
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
