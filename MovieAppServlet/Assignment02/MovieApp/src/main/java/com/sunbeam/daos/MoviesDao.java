package com.sunbeam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Movies;
import com.sunbeam.utils.DbUtil;

public class MoviesDao  implements AutoCloseable{
	private Connection con;
	public MoviesDao() {
		try {
			con =DbUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void close() throws Exception {
		if(con!=null)
			con.close();
	}
	
	public List<Movies> displayMovies() throws SQLException{
		String sql="select * from movies";
		List<Movies> list=new ArrayList<Movies>();
		try(PreparedStatement stmt =con.prepareStatement(sql)){
			try(ResultSet rs =stmt.executeQuery()){
				while(rs.next()) {
					int id=rs.getInt("id");
					String title=rs.getString("title");
					java.util.Date uDate=(rs.getDate("rel_date"));
					Movies m=new Movies(id,title,uDate);
					list.add(m);
				}
			}
			
		}
		return list;
		
	}

}
