package com.sunbeam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;
import com.sunbeam.utils.DbUtil;

public class UsersDao implements AutoCloseable {
	private Connection con;

	public UsersDao() {
		try {
			con = DbUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public int save(Users u) throws Exception {
		String sql = "INSERT INTO users VALUES(default, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getEmail());
			stmt.setString(6, u.getPassword());
			stmt.setString(4, u.getMobile());
			stmt.setDate(5, DateUtil.utilToSqlDate(u.getDate()));
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	public Users findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String email1 = rs.getString("email");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date uDate = DateUtil.sqlToUtilDate(rs.getDate("birth"));
					Users u = new Users();
					u.setId(id);
					u.setFirst_name(fname);
					u.setLast_name(lname);
					u.setEmail(email1);
					u.setPassword(password);
					u.setMobile(mobile);
					u.setDate(uDate);
					return u;
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}

	public int editUser(String fname, String lname, String email, String mobile, Date dob, Users u)
			throws SQLException {
		String sql = "update users set first_name=?,last_name=?,email=?, mobile=?, birth=? where id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, email);
			stmt.setString(4, mobile);
			stmt.setDate(5, DateUtil.utilToSqlDate(dob));
			stmt.setInt(6, u.getId());
			int cnt = stmt.executeUpdate();
			return cnt;
		} // stmt.close();
	}

	public List<Users> displayAll() throws Exception {
		List<Users> lt = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String mobile = rs.getString("mobile");
					java.util.Date uDate = DateUtil.sqlToUtilDate(rs.getDate("birth"));
					Users u = new Users(fname, lname, email, password, mobile, uDate);
					u.setId(id);
					lt.add(u);
				}
			} // rs.close();
		} // stmt.close();
		return lt;
	}

//	public int editUser(String mobile,Users u) throws SQLException {
//		String sql="update users set mobile=? where id=?";
//		try(PreparedStatement stmt = con.prepareStatement(sql)) {
//			stmt.setString(1,mobile);
//			stmt.setInt(2,u.getId());
//			int cnt =stmt.executeUpdate();
//			return cnt;
//		} //stmt.close();
//	}

	public int updatePassword(String password, Users u) throws SQLException {
		String sql = "UPDATE users SET password=? WHERE id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, password);
			stmt.setInt(2, u.getId());
			int cnt = stmt.executeUpdate();
			return cnt;
		} // stmt.close();
	}

}
