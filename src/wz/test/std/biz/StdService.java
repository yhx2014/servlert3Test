/*  
 * @(#) StdServer.java Create on 2014-9-22 下午9:29:17   
 *   
 * Copyright 2014 by pztx.   
 */


package wz.test.std.biz;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Part;

import org.apache.commons.lang3.math.NumberUtils;

import wz.test.std.dao.StdDao;
import wz.test.std.model.Page;
import wz.test.std.model.StdModel;
import wz.test.std.util.DateUtils;

/**
 * @StdServer.java
 * @created at 2014-9-22 下午9:29:17 by zhanghl
 *
 * @desc
 *
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class StdService {
	private StdDao dao = new StdDao();
	
	public Page<StdModel> findPage(Map<String, String> rq) throws Exception {
		return dao.findPage(rq);
	}

	public StdModel findById(String id) throws Exception {
		return this.dao.findById(id);
	}

	public int save(Map<String, String> rq, Part part) throws Exception {
		String id = rq.get("studentID");
		String name = rq.get("studentName");
		int age = NumberUtils.toInt(rq.get("studentAge"),0);
		String date = rq.get("studentBirthdate");
		Date d = DateUtils.parseDate(date);

		StdModel stdModel = new StdModel(id, name, age, d);
		
		InputStream in = null;
		if(part!=null){
			in = part.getInputStream();
		}
		
		int result = 0;
		String type = rq.get("type");
		if("add".equals(type)){
			result = this.dao.insert(stdModel, in);
		}else if("edit".equals(type)){
			result = this.dao.update(stdModel, in);
		}else if("del".equals(type)){
			result = this.dao.deleteById(rq.get("id"));
		}
		return result;
	}

	public OutputStream getImageById(String id) throws Exception {
		return this.dao.getImage(id);
	}

	public boolean checkExist(String string) throws Exception {
		return this.dao.checkExist(string) > 0;
	}
}
