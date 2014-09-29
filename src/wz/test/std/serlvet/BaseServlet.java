/*  
 * @(#) BaseServlet.java Create on 2014-9-22 下午9:15:18   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.serlvet;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wz.test.std.biz.StdService;
import wz.test.std.util.LogEable;

/**
 * @BaseServlet.java
 * @created at 2014-9-22 下午9:15:18 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class BaseServlet extends HttpServlet implements LogEable {

	private static final String BASE_PAGE_DIR = "/WEB-INF/page/";
	protected StdService service = new StdService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void dispatcher(HttpServletRequest request,
			HttpServletResponse response, String url) throws Exception {
		url = BASE_PAGE_DIR + url;
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void redirect(HttpServletResponse response, String url)
			throws Exception {
		response.sendRedirect(url);
	}

	protected Map<String, String> requestToMap(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		Enumeration<String> es = request.getParameterNames();
		while (es.hasMoreElements()) {
			String key = es.nextElement();
			result.put(key, request.getParameter(key));
		}
		request.setAttribute("rqp", result);
		logger.debug("request params:" + result);
		return result;
	}
}
