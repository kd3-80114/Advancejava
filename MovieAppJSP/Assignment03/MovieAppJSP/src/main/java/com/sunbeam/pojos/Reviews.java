package com.sunbeam.pojos;

import java.sql.Timestamp;

public class Reviews {
	private int id;
	private int movie_id;
	private String review;
	private int rating;
	private int users_id;
	private Timestamp modified;

	public Reviews() {
	}

	public Reviews(int id, int movie_id, int rating, String review, int users_id, Timestamp modified) {
		this.id = id;
		this.movie_id = movie_id;
		this.review = review;
		this.rating = rating;
		this.users_id = users_id;
		this.modified = modified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	@Override
	public String toString() {
		return "Reviews [id=" + id + ", movie_id=" + movie_id + ", review=" + review + ", rating=" + rating
				+ ", users_id=" + users_id + ", modified=" + modified + "]";
	}

}
