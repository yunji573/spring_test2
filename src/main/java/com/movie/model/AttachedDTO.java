package com.movie.model;

import lombok.Data;

@Data
public class AttachedDTO {
	private int id;
	private int movieId;
	private String path;
}