package com.sunbeam.beans;
//package com.sunbeam.beans;
//
//import com.sunbeam.daos.ReviewsDao;
//import com.sunbeam.pojos.Reviews;
//
//public class MovieListBean {
//	private int reviewId;
//	private Reviews reviews;
//
//	public MovieListBean() {
//		reviews = null;
//	}
//
//	public int getReviewId() {
//		return reviewId;
//	}
//
//	public void setReviewId(int reviewId) {
//		this.reviewId = reviewId;
//	}
//
//	public Reviews getReviews() {
//		return reviews;
//	}
//
//	public void setReviews(Reviews reviews) {
//		this.reviews = reviews;
//	}
//
//	public void fetchReview() {
//		try (ReviewsDao dao = new ReviewsDao()) {
//			reviews = dao.displayWithId(reviewId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//}
