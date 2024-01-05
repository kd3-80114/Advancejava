package com.sunbeam.beans;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Reviews;

public class FindReviewBean {
	private int id;
	private Reviews reviews;

	public FindReviewBean() {
	}

	public Reviews getReviews() {
		return reviews;
	}

	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void fetchReview() {
		try (ReviewsDao dao = new ReviewsDao()) {
			reviews = dao.displayWithId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
