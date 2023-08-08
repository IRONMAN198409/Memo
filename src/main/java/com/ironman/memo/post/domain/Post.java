package com.ironman.memo.post.domain;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Post {

	private int id;
	private int userId;
	private String title;
	private String content;
	private String imagePath;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
