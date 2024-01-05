package com.sunbeam.beans;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Reviews;

public class UpdateReviewBean {
	private int id;
	private int movieId;
	private int rating;
	private String review;
	private String msg;

	public UpdateReviewBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void updateReview() {
		try (ReviewsDao dao = new ReviewsDao()) {
			Reviews reviews = new Reviews(id, movieId, rating, review, id, null);
			int cnt = dao.update(reviews);
			if (cnt == 1) {
				msg = cnt + " Review Updated Successfully!";
			} else {
				msg = "Error Occured! Retry Again!!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
