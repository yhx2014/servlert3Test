/*  
 * @(#) StdModel.java Create on 2014-9-22 下午8:26:37   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.model;

import java.io.File;
import java.util.Date;

/**
 * @StdModel.java
 * @created at 2014-9-22 下午8:26:37 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class StdModel {


	public static final String TABLE_NAME = "std";

	// studentID 学号(身份证号) 字符型 18位 主键
	// studentName 姓名 字符 12
	// studentName 年龄 数值
	// studentBirthdate 生日
	// studentPicture 学生照片 图片
	private String studentID;
	private String studentName;
	private Integer studentAge;
	private Date studentBirthdate;
	private File studentPicture;
	
	//1 有图片，0 没有
	private int existPic;

	public StdModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StdModel(String studentID, String studentName, Integer studentAge,
			Date studentBirthdate) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentBirthdate = studentBirthdate;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public Date getStudentBirthdate() {
		return studentBirthdate;
	}

	public void setStudentBirthdate(Date studentBirthdate) {
		this.studentBirthdate = studentBirthdate;
	}

	public File getStudentPicture() {
		return studentPicture;
	}

	public void setStudentPicture(File studentPicture) {
		this.studentPicture = studentPicture;
	}
	public int getExistPic() {
		return existPic;
	}
	public void setExistPic(int existPic) {
		this.existPic = existPic;
	}
}
