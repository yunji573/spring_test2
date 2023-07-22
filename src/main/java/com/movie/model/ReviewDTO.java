package com.movie.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewDTO {
    
	private int id;
	private int userId;
	private int movieId;
	private int rating;
	private String review;
	private String writerNickname;
 
 
}
