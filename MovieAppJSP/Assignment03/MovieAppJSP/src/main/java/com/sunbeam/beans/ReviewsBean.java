package com.sunbeam.beans;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.ReviewsDao;
import com.sunbeam.pojos.Reviews;
import com.sunbeam.pojos.Users;

public class ReviewsBean {
	private Users users;
	private String type;
	private List<Reviews> list;
	private String msg;

	public ReviewsBean() {
		list = new ArrayList<Reviews>();
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Reviews> getList() {
		return list;
	}

	public void setList(List<Reviews> list) {
		this.list = list;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void fetchReviews() {
		try (ReviewsDao dao = new ReviewsDao()) {
			if (type == null || type.equals("all")) {
				list = dao.displayallReviews();
				msg = "All Reviews";
			} else if (type.equals("my")) {
				list = dao.displayReviews(users);
				msg = "My Reviews";
			} else if (type.equals("shared")) {
				List<Integer> lt = dao.sharedReview(users);
				for (Integer integer : lt) {
					list.add(dao.displayWithId(integer));
				}
				msg = "Shared Reviews";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
