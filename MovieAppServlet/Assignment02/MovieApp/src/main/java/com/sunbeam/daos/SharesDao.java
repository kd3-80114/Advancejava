package com.sunbeam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Shares;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DbUtil;

public class SharesDao implements AutoCloseable {
	private Connection con;

	public SharesDao() {
		try {
			con = DbUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		if (con != null)
			con.close();
	}

	public List<Shares> displaySharedReview() throws SQLException{
		String sql="select * from shares";
		List<Shares> list=new ArrayList<>();
		try(PreparedStatement stmt =con.prepareStatement(sql)){
			try(ResultSet rs =stmt.executeQuery()){
				int reviewID=rs.getInt("review_id");
				int userID=rs.getInt("user_id");
				Shares s=new Shares(reviewID,userID);
				list.add(s);
			}
			return list;
		}
		
	}
	public List<Shares> getSharesdId(Users u) throws SQLException{
		String sql="select * from shares where user_id=?";
		List<Shares> list=new ArrayList<Shares>();
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, u.getId());
			try(ResultSet rs= stmt.executeQuery()){
				while(rs.next()) {
					int reviewId=rs.getInt("review_id");
					int userId=rs.getInt("user_id");
					Shares s= new Shares(reviewId,userId);
					list.add(s);
				}
			}
			return list;
		}	
	}

}
