package com.haui.android.sns.entity;

import org.joda.time.DateTime;

public class Post {

	public static final String CONTENT_TAG = "content";
	public static final String USER_TAG = "user_id";
	public static final String POST_AT_TAG = "post_at";

	private int user;
	private String content;
	private DateTime postTime;

	public Post(int user, String content, DateTime postTime) {

		this.user = user;
		this.content = content;
		this.postTime = postTime;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(DateTime postTime) {
		this.postTime = postTime;
	}

}
