/*  
 * @(#) DateUtils.java Create on 2014-9-22 下午10:35:47   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @DateUtils.java
 * @created at 2014-9-22 下午10:35:47 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static final String DF_YYYYMMDD = "yyyy-MM-dd";
	private static String[] parsePatterns = { DF_YYYYMMDD,
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	public static String getDate(Date date) {
		return DateFormatUtils.format(date, DF_YYYYMMDD);
	}

	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
}
