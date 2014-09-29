/*  
 * @(#) JdbcPropertiesUtil.java Create on 2014-9-22 下午8:39:19   
 *   
 * Copyright 2014 by pztx.   
 */


package wz.test.std.util;

import java.util.ResourceBundle;

/**
 * @JdbcPropertiesUtil.java
 * @created at 2014-9-22 下午8:39:19 by zhanghl
 *
 * @desc
 *
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class JdbcPropertiesUtil {
	 private static final String BUNDLE_NAME = "jdbc";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	public static String getValue(String key){
		String result = RESOURCE_BUNDLE.getString(key);
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getValue("jdbc.url"));
	}
}
