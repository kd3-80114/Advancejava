package com.sunbeam.beans;

import com.sunbeam.daos.ReviewsDao;

public class ShareReviewBean {
	private int id;
	private int userId;
	private String msg;

	public ShareReviewBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void shareReview() {
		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.shareReviewWith(id, userId);
			if (cnt == 1) {
				msg = cnt + " Review Updated Successfullly!";
			} else {
				msg = "Error Occured! Retry Again!!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
