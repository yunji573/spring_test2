package com.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String showIndex(HttpSession session) {
		
		String uploadPath =session.getServletContext().getRealPath("/");
	 
		 
		return "index";
	}

}
