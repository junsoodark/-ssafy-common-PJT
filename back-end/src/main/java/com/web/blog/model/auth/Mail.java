package com.web.blog.model.auth;

public class Mail {
	private String to;
	private String title;
	private String message;

	public Mail() {

	}

	public Mail(String to, String title, String message) {
		this.to = to;
		this.title = title;
		this.message = message;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
