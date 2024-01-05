package com.sunbeam.beans;

import com.sunbeam.daos.ReviewsDao;

public class DelReviewBean {
	private int id;
	private String msg;

	public DelReviewBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void delReview() {
		try (ReviewsDao dao = new ReviewsDao()) {
			int cnt = dao.deleteReview(id);
			if (cnt == 1) {
				msg = cnt + " Review Deleted Successfully!";
			} else {
				msg = "Error Occured! Retry Again!!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
