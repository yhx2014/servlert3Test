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

import wz.test.std.model.Page;
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
@WebServlet(urlPatterns={"/std/list","/index"})
public class StdListServlet extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "std/list.jsp";
		try {
			Map<String,String> rq = requestToMap(req);
			String oprType = rq.get("type");
			String result = rq.get("result");
			result = "true".equals(result)?"成功":"失败";
			String msg = "";
			if("add".equals(oprType)){
				msg = "新增 " + result;
			}else if("edit".equals(oprType)){
				msg = "修改" + result;
			}else if("del".equals(oprType)){
				msg = "删除" + result;
			}
			req.setAttribute("msg", msg);
			Page<StdModel> page = service.findPage(rq);
			req.setAttribute("page", page);
			dispatcher(req, resp, url );
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
