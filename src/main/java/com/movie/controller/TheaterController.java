package com.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.model.TheaterDTO;
import com.movie.service.TheaterService;

@RequestMapping("/theater/")
@Controller
public class TheaterController {
	private final TheaterService THEATER_SERVICE;

	public TheaterController(TheaterService theaterService) {
		this.THEATER_SERVICE = theaterService;

	}

	@GetMapping("/admin/write")
	public String showWrite() {

		return "/theater/theaterwrite";
	}

	@PostMapping("/admin/write")
	public String write(Authentication auth, HttpSession session, TheaterDTO theaterDTO) {

		THEATER_SERVICE.insert(theaterDTO);

		return "redirect:/movie/admin/list/1";
	}

	@GetMapping("admin/theaterlist/{pageNo}")
	public String listAll(@PathVariable int pageNo, Model model) {

		int maxPage = THEATER_SERVICE.selectMaxPage();
		int startPage = 0;
		int endPage = 0;

		// 만약 pageNo가 5이하일 경우
		if (maxPage <= 5) {
			startPage = 1;
			endPage = maxPage;
			// 만약 pageNo가 3이하일 경우
		} else if (pageNo <= 3) {
			startPage = 1;
			endPage = 5;
		} else if (pageNo >= maxPage - 2) {
			// 만약 pageNo가 maxpage -2 이상일경우
			startPage = maxPage - 4;
			endPage = maxPage;
		} else {
			// 그외 경우
			startPage = pageNo - 2;
			endPage = pageNo + 2;
		}

		model.addAttribute("list", THEATER_SERVICE.selectAll(pageNo));

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/theater/theaterlist";
	}

	@GetMapping("printAll/{pageNo}")
	public String printAll(@PathVariable int pageNo, Model model) {

		int maxPage = THEATER_SERVICE.selectMaxPage();
		int startPage = 0;
		int endPage = 0;

		// 만약 pageNo가 5이하일 경우
		if (maxPage <= 5) {
			startPage = 1;
			endPage = maxPage;
			// 만약 pageNo가 3이하일 경우
		} else if (pageNo <= 3) {
			startPage = 1;
			endPage = 5;
		} else if (pageNo >= maxPage - 2) {
			// 만약 pageNo가 maxpage -2 이상일경우
			startPage = maxPage - 4;
			endPage = maxPage;
		} else {
			// 그외 경우
			startPage = pageNo - 2;
			endPage = pageNo + 2;
		}

		model.addAttribute("list", THEATER_SERVICE.selectAll(pageNo));
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/theater/theaterlist";
	}

	@GetMapping("showinglist/{id}")
	public String showinglist(@PathVariable int id, Model model) {

		// 영화제목1개당 1개이상의 time을 산출하기위해 map 사용
		Map<String, String[]> multiMap = new HashMap<>();
		multiMap = THEATER_SERVICE.selectbytheater(id);

		model.addAttribute("list", multiMap);

		return "/theater/showinglist";
	}

	@GetMapping("admin/theaterpricnone/{id}")
	public String AdminprintOne(@PathVariable int id, Model model) {

		TheaterDTO theaterDTO = THEATER_SERVICE.selectOne(id);
		model.addAttribute("theaterDTO", theaterDTO);

		return "/theater/theaterprintone";
	}

	@GetMapping("admin/update/{id}")
	public String update(@PathVariable int id, Model model) {
		TheaterDTO theaterDTO = THEATER_SERVICE.selectOne(id);
		model.addAttribute("theaterDTO", theaterDTO);

		return "/theater/theaterupdate";
	}

	@PostMapping("admin/update/{id}")
	public String update(@PathVariable int id, TheaterDTO theaterDTO) {

		THEATER_SERVICE.update(theaterDTO);

		return "redirect:/theater/admin/list/1";
	}
	
	@GetMapping("admin/list/{pageNo}")
	public String list(@PathVariable int pageNo, Model model) {

		int maxPage = THEATER_SERVICE.selectMaxPage();
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

		model.addAttribute("list", THEATER_SERVICE.selectAll(pageNo));
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/theater/adminlist";
	}
	
	@GetMapping("admin/delete/{id}")
	public String delete(@PathVariable int id, Authentication auth){
			
		THEATER_SERVICE.delete(id);
		return "redirect:/theater/admin/list/1";
		
		
		
	}
	
	
}
