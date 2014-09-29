/*  
 * @(#) StdListServlet.java Create on 2014-9-22 下午9:14:14   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.serlvet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import wz.test.std.model.StdModel;

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
@WebServlet(urlPatterns = "/std/detail")
public class StdDetailServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "std/detail.jsp";
		try {
			Map<String,String> rq = requestToMap(req);
			String id = rq.get("id");
			
			StdModel stdModel = new StdModel();
			
			String type = "add";
			if(StringUtils.isNotBlank(id)){
				stdModel = this.service.findById(id);
				type = "edit";
			}
			req.setAttribute("type", type);
			req.setAttribute("model", stdModel);
			dispatcher(req, resp, url);
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
