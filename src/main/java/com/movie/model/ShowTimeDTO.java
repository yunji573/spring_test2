package com.movie.model;

import java.sql.Date;

import lombok.Data;


 

@Data
public class ShowTimeDTO {
	private int id;
	private int movieId;
	private int theaterId;
	private String time;
	private Date startDate;
	private Date endDate;
	private String theatername;  
	private String title;       
}
