/*  
 * @(#) LogEable.java Create on 2014-9-22 下午9:06:58   
 *   
 * Copyright 2014 by pztx.   
 */


package wz.test.std.util;

import org.apache.log4j.Logger;

/**
 * @LogEable.java
 * @created at 2014-9-22 下午9:06:58 by zhanghl
 *
 * @desc
 *
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public interface LogEable {
	Logger logger = Logger.getLogger(LogEable.class);
}
