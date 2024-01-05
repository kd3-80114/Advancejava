package com.sunbeam.pojos;

import java.util.Date;

public class Movies {
	private int id;
	private String title;
	private Date released;
	
	public Movies() {
	}

	public Movies(int id, String title, Date released) {
		super();
		this.id = id;
		this.title = title;
		this.released = released;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	@Override
	public String toString() {
		return "Movies [id=" + id + ", title=" + title + ", released=" + released + "]";
	}
	
	
}
