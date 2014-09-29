/*  
 * @(#) StdDao.java Create on 2014-9-22 下午9:29:25   
 *   
 * Copyright 2014 by pztx.   
 */

package wz.test.std.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import wz.test.std.filter.DBConnectionFilter;
import wz.test.std.model.Page;
import wz.test.std.model.StdModel;

/**
 * @StdDao.java
 * @created at 2014-9-22 下午9:29:25 by zhanghl
 * 
 * @desc
 * 
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class StdDao {

	private static final int DEFAULT_PAGE_SIZE = 5;

	public Page<StdModel> findPage(Map<String, String> rq) throws Exception {
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer countBuffer = new StringBuffer();
		StringBuffer conditonBuffer = new StringBuffer();
		List<Object> params = new ArrayList<>();

		String tableName = StdModel.TABLE_NAME;
		sqlBuffer
				.append("SELECT studentID,studentName,studentAge,studentBirthdate FROM "
						+ tableName);
		countBuffer.append("SELECT count(*) FROM " + tableName);

		conditonBuffer.append(" WHERE 1=1");

		String name = rq.get("q_name");
		if (StringUtils.isNotBlank(name)) {
			conditonBuffer.append(" AND (studentName LIKE ? OR studentID LIKE ?) ");
			params.add("%" + name + "%");
			params.add("%" + name + "%");
		}

		int pageSize = NumberUtils.toInt(rq.get("q_pageSize"),
				DEFAULT_PAGE_SIZE);
		int pageNow = NumberUtils.toInt(rq.get("q_pageNow"), 1);

		int sumCount = 0;
		List<StdModel> list = new ArrayList<>();

		sqlBuffer.append(conditonBuffer);
		ResultSet itemRes = getPageResultSet(sqlBuffer.toString(), pageNow,
				pageSize, params);
		while (itemRes.next()) {
			list.add(exchageModel(itemRes));
		}

		countBuffer.append(conditonBuffer);
		ResultSet countRes = getResultSet(countBuffer.toString(), params);
		countRes.next();
		sumCount = countRes.getInt(1);

		return new Page<>(pageSize, pageNow, sumCount, list);
	}

	public int insert(StdModel model, InputStream in) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into " + StdModel.TABLE_NAME);
		// studentID 学号(身份证号) 字符型 18位 主键
		// studentName 姓名 字符 12
		// studentName 年龄 数值
		// studentBirthdate 生日
		// studentPicture 学生照片 图片
		if (in != null) {
			sql.append(" (studentID,studentName,studentAge,studentBirthdate,studentPicture) values(?,?,?,?,?)");
		} else {
			sql.append(" (studentID,studentName,studentAge,studentBirthdate) values(?,?,?,?)");
		}

		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());

		int index = 1;
		ps.setString(index++, model.getStudentID());
		ps.setString(index++, model.getStudentName());
		ps.setInt(index++, model.getStudentAge());
		ps.setDate(index++, new java.sql.Date(model.getStudentBirthdate()
				.getTime()));
		if (in != null) {
			ps.setBinaryStream(index++, in, in.available());
		}

		int result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public int update(StdModel model, InputStream in) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update " + StdModel.TABLE_NAME);
		// studentID 学号(身份证号) 字符型 18位 主键
		// studentName 姓名 字符 12
		// studentName 年龄 数值
		// studentBirthdate 生日
		// studentPicture 学生照片 图片
		sql.append(" set studentName=?,studentAge=?,studentBirthdate=?");
		if (in != null) {
			sql.append(",studentPicture=?");
		}
		sql.append(" WHERE studentID=?");
		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());

		int index = 1;
		ps.setString(index++, model.getStudentName());
		ps.setInt(index++, model.getStudentAge());
		ps.setDate(index++, new java.sql.Date(model.getStudentBirthdate()
				.getTime()));
		if (in != null) {
			ps.setBinaryStream(index++, in, in.available());
		}
		ps.setString(index++, model.getStudentID());

		int result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public OutputStream getImage(String id) throws Exception {
		String sql = "select studentPicture from " + StdModel.TABLE_NAME
				+ " where studentID=?";
		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ps.setObject(1, id);
		ResultSet result = ps.executeQuery();
		if (result.next()) {

			InputStream fis = result.getBinaryStream(1);
			ByteArrayOutputStream outputStraem = new ByteArrayOutputStream();
			byte[] b = new byte[10 * 1024];
			int length = 0;
			while ((length = fis.read(b)) != -1) {
				outputStraem.write(b, 0, length);
			}
			return outputStraem;
		}
		return null;
	}

	public StdModel findById(String id) throws Exception {
//		String sql = "SELECT studentID,studentName,studentAge,studentBirthdate,if(isnull(studentPicture),0,1) as existPic FROM "
//				+ StdModel.TABLE_NAME;
		String sql = "SELECT studentID,studentName,studentAge,studentBirthdate,NVL2(studentPicture, '1', '0') as existPic FROM "
				+ StdModel.TABLE_NAME;
		sql += " WHERE studentID=?";

		StdModel result = null;
		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setObject(1, id);
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			result = exchageModel(res);
			result.setExistPic(res.getInt("existPic"));
		}

		return result;
	}

	public StdModel exchageModel(ResultSet res) throws SQLException {
		if (res != null) {
			String name = res.getString("studentName");
			int age = res.getInt("studentAge");
			Date birthdate = res.getDate("studentBirthdate");
			String id = res.getString("studentID");
			return new StdModel(id, name, age, birthdate);
		}
		return null;
	}

	private ResultSet getPageResultSet(String sql, int pageNow, int pageSize,
			List<Object> params) throws Exception {
		String sql2 = sql;
		sql2 += " ORDER BY studentID DESC";
		sql2 += " LIMIT " +  (pageNow-1)* pageSize + "," + pageSize;
		return getResultSet(sql2, params);
	}

	private ResultSet getResultSet(String sql, List<Object> params)
			throws Exception {
		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		if (params != null && params.size() > 0) {
			int index = 1;
			for (Object object : params) {
				ps.setObject(index++, object);
			}
		}
		return ps.executeQuery();
	}

	public int deleteById(String id) throws SQLException {
		String sql = "DELETE FROM " + StdModel.TABLE_NAME + " WHERE studentID=?";
		Connection conn = DBConnectionFilter.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ps.setString(1, id);
		int result = ps.executeUpdate();
		ps.close();
		return result;
	}

	public Integer checkExist(String string) throws Exception {
		String sql = "select count(*) from " + StdModel.TABLE_NAME + " WHERE studentID=?";
		List<Object> params = new ArrayList<>();
		params.add(string);
		ResultSet res = this.getResultSet(sql, params );
		if(res.next()){
			return res.getInt(1);
		}
		return null;
	}
}
