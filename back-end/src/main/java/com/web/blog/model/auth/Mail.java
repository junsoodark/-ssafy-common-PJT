package com.web.blog.model.auth;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
	private String to;
	private String title;
	private String message;

}
