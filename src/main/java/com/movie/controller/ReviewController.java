package com.movie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.movie.model.ReviewDTO;
import com.movie.model.UserDTO;
import com.movie.service.ReviewService;

@RequestMapping("/review/")
@Controller
public class ReviewController {
	
	private final ReviewService REVIEW_SERVICE;
	
	
	public ReviewController(ReviewService reviewService){
		this.REVIEW_SERVICE = reviewService;
		
	}

	
   @ResponseBody
   @PostMapping(value="write/{movieId}",produces="application/text; charset=utf8")
   public String write(Authentication auth, ReviewDTO reviewDTO, @PathVariable int movieId) {
	   JsonObject jsonObject = new JsonObject();
	   
	   
	   UserDTO logIn = (UserDTO) auth.getPrincipal();
	   
	   reviewDTO.setMovieId(movieId);
	   reviewDTO.setUserId(logIn.getId());
	   
	   REVIEW_SERVICE.insert(reviewDTO);
	   jsonObject.addProperty("result", "success");
	   jsonObject.addProperty("message", "!!!댓글등록성공!!!");
	 
		
		return jsonObject.toString();
	   
	 
   }
   
   @ResponseBody
   @GetMapping(value="printAll/{movieId}",produces="application/json; charset=utf8")
	public String printOne(@PathVariable int movieId, HttpSession session, Authentication authentication, Model model) {
		System.out.println("review printOne");
		UserDTO logIn = (UserDTO) authentication.getPrincipal();

		Gson gson = new GsonBuilder().create();
		
		
		List<ReviewDTO> rList = REVIEW_SERVICE.selectAllByMovieId(movieId);
	
	    model.addAttribute("rList", REVIEW_SERVICE.selectAllByMovieId(movieId));
	
	    model.addAttribute("logInId", logIn.getId());
	
	  
	    return gson.toJson(rList);
	}
   
   @GetMapping("delete/{id}")
	public String delete(@PathVariable int id, Authentication auth) {
		UserDTO logIn = (UserDTO) auth.getPrincipal();

		ReviewDTO reviewDTO = REVIEW_SERVICE.selectOne(id);
		
		if (reviewDTO == null) {
			
			return "redirect:/movie/printOne"+reviewDTO.getMovieId();
		}

		if (logIn.getId() != reviewDTO.getUserId()) {
			return "redirect:/movie/printOne/" + reviewDTO.getMovieId();
		}

		REVIEW_SERVICE.delete(id);

		return "redirect:/movie/printOne"+reviewDTO.getMovieId();
	}
}
 
