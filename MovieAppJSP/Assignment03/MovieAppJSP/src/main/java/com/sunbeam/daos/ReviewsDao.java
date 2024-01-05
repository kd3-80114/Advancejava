package com.sunbeam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;
import com.sunbeam.utils.DateUtil;
import com.sunbeam.utils.DbUtil;

public class ReviewsDao implements AutoCloseable {

	private Connection con;

	public ReviewsDao() {
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

	public List<Reviews> displayallReviews() throws SQLException {
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "select * from reviews";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int movieId = rs.getInt("movie_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					int userId = rs.getInt("user_id");
					Timestamp modified = rs.getTimestamp("modified");

					Reviews r1 = new Reviews();
					r1.setId(id);
					r1.setMovie_id(movieId);
					r1.setReview(review);
					r1.setRating(rating);
					r1.setUsers_id(userId);
					r1.setModified(modified);
					list.add(r1);
				}
			}
		}
		return list;
	}

	public List<Reviews> displayReviews(Users r) throws SQLException {
		List<Reviews> list = new ArrayList<Reviews>();
		String sql = "select * from reviews where user_id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int movie_id = rs.getInt("movie_id");
					String review = rs.getString("review");
					int user_id = rs.getInt("user_id");
					int rating = rs.getInt("rating");
					Reviews r1 = new Reviews();
					r1.setReview(review);
					r1.setId(id);
					r1.setMovie_id(movie_id);
					r1.setUsers_id(user_id);
					r1.setRating(rating);
					list.add(r1);
				}
			}
		}
		return list;
	}

	public int addReview(int movieId, String rev, int rating, Users u) throws SQLException {
		String sql = "insert into reviews values(default,?,?,?,?,now())";
		int cnt;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, movieId);
			stmt.setString(2, rev);
			stmt.setInt(3, rating);
			stmt.setInt(4, u.getId());
			cnt = stmt.executeUpdate();
		}
		return cnt;

	}

//	public int updateReview(int movieId, String rev, Users u) throws SQLException {
//		String sql = "update reviews set review=? where movie_id=?";
//		int cnt;
//		try (PreparedStatement stmt = con.prepareStatement(sql)) {
//			stmt.setString(1, rev);
//			stmt.setInt(2, movieId);
//			cnt = stmt.executeUpdate();
//		}
//		return cnt;
//	}

	public int update(Reviews r) throws Exception {
		String sql = "UPDATE reviews SET movie_id=?, review=?, rating=?, modified=now() WHERE id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getMovie_id());
			stmt.setString(2, r.getReview());
			stmt.setInt(3, r.getRating());
			stmt.setInt(4, r.getId());
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	public int deleteReview(int delId) throws Exception {
		String sql = "DELETE FROM reviews WHERE id = ?";
		int cnt;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, delId);
			cnt = stmt.executeUpdate();

		}
		return cnt;// rs.close();
	} // stmt.close();

	public int shareReviewWith(int id, int u) throws Exception {
		String sql = "INSERT INTO shares VALUES(?, ?)";
		int cnt;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.setInt(2, u);
			cnt = stmt.executeUpdate();
			return cnt;
		}
	}

	public List<Integer> sharedReview(Users u) throws Exception {
		List<Integer> lt = new ArrayList<Integer>();
		String sql = "SELECT review_id FROM shares WHERE user_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, u.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("review_id");
					lt.add(id);
				}
			}
		}
		return lt;
	}

	public Reviews displayWithId(Integer a) throws Exception {
		String sql = "SELECT * FROM reviews WHERE id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, a);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int movie_id = rs.getInt("movie_id");
					int user_id = rs.getInt("user_id");
					String review = rs.getString("review");
					int rating = rs.getInt("rating");
					Timestamp modified = rs.getTimestamp("modified");
					Reviews r = new Reviews(id, movie_id, rating, review, user_id, modified);
					return r;
				}
			}
			return null;
		}

	}
}
