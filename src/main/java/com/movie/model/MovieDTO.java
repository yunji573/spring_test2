package com.movie.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MovieDTO {
    
	private int id;
	private String title;
	private String synopsis;
	private String grade;
	private String imaname;
	private Timestamp entryDate;
	private Timestamp modifyDate;
	private double averageRating;
 
}
