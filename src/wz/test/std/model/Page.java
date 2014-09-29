/*  
 * @(#) Page.java Create on 2014-9-22 下午9:39:31   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.model;

import java.util.List;

/**
 * @Page.java
 * @created at 2014-9-22 下午9:39:31 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class Page<T> {
	private int pageSize;
	private int pageNow;
	private int sumCount;
	private int sumPage;
	private List<T> items;

	public Page(int pageSize, int pageNow, int sumCount,
			List<T> items) {
		super();
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.items = items;
		this.sumCount = sumCount;
		
		if(pageSize>0 && sumCount>0){
			sumPage = (sumCount+pageSize-1)/pageSize;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
