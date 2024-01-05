package com.sunbeam.beans;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Users;

public class AddReviewBean {
	private int movieId;
	private int rating;
	private String review;
	private Users user;
	private String msg;

	public AddReviewBean() {
		this.msg = "";
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void addReview() {
		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.addReview(movieId, review, rating, user);
			if (cnt == 1) {
				msg = cnt + " Review Added Successfully!";
			} else {
				msg = "Error Occured! Retry Again!!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
