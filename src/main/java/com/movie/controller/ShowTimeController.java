package com.movie.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.model.AttachedDTO;
import com.movie.model.MovieDTO;
import com.movie.model.ShowTimeDTO;
import com.movie.model.UserDTO;
import com.movie.service.ShowTimeService;
import com.movie.service.TheaterService;

@RequestMapping("/showtime/")
@Controller
public class ShowTimeController {

	private final ShowTimeService SHOWTIME_SERVICE;
 

	public ShowTimeController(ShowTimeService showtimeService ) {

		this.SHOWTIME_SERVICE = showtimeService;
	 
	}

	@GetMapping("/admin/write")
	public String showtimeWrite(Model model, ShowTimeDTO showtimeDTO) {
        
	 	List<ShowTimeDTO> showtimeList=SHOWTIME_SERVICE.selectAll();
	 	model.addAttribute("showtimeList", showtimeList);
	
	 	return "/showtime/timewrite";
	}

	@PostMapping("/admin/write")
	public String showtimeWrite(Authentication auth, HttpSession session, @RequestParam String[] time,
			@RequestParam int movieId, @RequestParam int theaterId, @RequestParam Date startDate,
			@RequestParam Date endDate) {

 
		ArrayList<ShowTimeDTO> showtimeList = new ArrayList<>();

       //여러개 입력된 상영시간을 id별 상영시간으로 리스트로 저장하여 insert처리 
		
		for (String selectTime : time) {
			ShowTimeDTO showtimeDTO = new ShowTimeDTO();
			showtimeDTO.setMovieId(movieId);
			showtimeDTO.setTheaterId(theaterId);
			showtimeDTO.setStartDate(startDate);
			showtimeDTO.setEndDate(endDate);
			showtimeDTO.setTime(selectTime);

			showtimeList.add(showtimeDTO);
		}

		SHOWTIME_SERVICE.insert(showtimeList);
 
		return "redirect:/showtime/admin/timelist/1";
	}

	
	@GetMapping("admin/timelist/{pageNo}")
	public String listAll(@PathVariable int pageNo, Model model) {

		int maxPage = SHOWTIME_SERVICE.selectMaxPage();
		int startPage = 0;
		int endPage = 0;

		if (maxPage <= 5) {
			startPage = 1;
			endPage = maxPage;

		} else if (pageNo <= 3) {
			startPage = 1;
			endPage = 5;
		} else if (pageNo >= maxPage - 2) {

			startPage = maxPage - 4;
			endPage = maxPage;
		} else {

			startPage = pageNo - 2;
			endPage = pageNo + 2;
		}

		model.addAttribute("list", SHOWTIME_SERVICE.selectAll2(pageNo));
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/showtime/timeList";
	}
	
	@GetMapping("admin/timePrintOne/{id}")
	public String AdminprintOne(@PathVariable int id, HttpSession session, Authentication authentication, Model model) {

		   
		ShowTimeDTO showTimeDTO = SHOWTIME_SERVICE.selectOne(id);
		model.addAttribute("showTimeDTO", showTimeDTO);

		return "/showtime/timePrintOne";
	}
	
	@GetMapping("admin/update/{id}")
	public String timeUpdate(@PathVariable int id, Model model, Authentication auth) {
 
		ShowTimeDTO showTimeDTO = SHOWTIME_SERVICE.selectOne(id);

		if (showTimeDTO == null) {
			return "redirect:/showtime/admin/timelist/1";
		}

		model.addAttribute("showTimeDTO", showTimeDTO);
 
		return "/showtime/timeUpdate";

	}
	
	
	@PostMapping("admin/update/{id}")
	public String update(@PathVariable int id,ShowTimeDTO showTimeDTO) {
		
		showTimeDTO.setId(id);
		SHOWTIME_SERVICE.update(showTimeDTO);
				 
		 
		return "redirect:/showtime/admin/timelist/1";
}
	@GetMapping("admin/delete/{id}")
	public String delete(@PathVariable int id, Authentication auth){
	  
		SHOWTIME_SERVICE.delete(id);
		
		return "redirect:/showtime/admin/timelist/1";
		
		
		
	}
	
	}
