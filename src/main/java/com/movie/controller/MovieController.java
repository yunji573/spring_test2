package com.movie.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.movie.model.AttachedDTO;
import com.movie.model.MovieDTO;
import com.movie.model.ReviewDTO;
import com.movie.model.UserDTO;
import com.movie.service.AttachedService;
import com.movie.service.MovieService;
import com.movie.service.ReviewService;

@RequestMapping("/movie/")
@Controller
/* @Secured("ADMIN") */
public class MovieController {

	private final MovieService MOVIE_SERVICE;
	private final AttachedService ATTACH_SERVICE;
	private final ReviewService REVIEW_SERVICE;
	private final String UPLOAD_PATH = "/upload/";

	public MovieController(MovieService movieService, AttachedService attachedService,ReviewService reviewService) {

		this.MOVIE_SERVICE = movieService;
		this.ATTACH_SERVICE = attachedService;
		this.REVIEW_SERVICE = reviewService;
	}

	@GetMapping("/admin/write")
	public String showWrite() {

		return "/movie/write";
	}

	/* @PreAuthorize("hasRole('ROLE_ADMIN')") */
	@Secured("ROLE_ADMIN")
	@PostMapping("/admin/write")
	public String write(Authentication auth, HttpSession session, @RequestParam("file") List<MultipartFile> files,
			@RequestParam String title, @RequestParam String synopsis, @RequestParam String grade) {
		/* List<MultipartFile> files로 파일 복수업로드 가능 */

		UserDTO logIn = (UserDTO) auth.getPrincipal();
 
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setTitle(title);
		movieDTO.setSynopsis(synopsis);
		movieDTO.setGrade(grade);
	 

		MOVIE_SERVICE.insert(movieDTO);
	  
		String uploadPath = session.getServletContext().getRealPath("/") + "upload/" + movieDTO.getId();
	 
 
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		ArrayList<AttachedDTO> attachedList = new ArrayList<>();

		try {
			for (MultipartFile file : files) {
				File temp = new File(uploadPath, file.getOriginalFilename());
				file.transferTo(temp);

				AttachedDTO attachedDTO = new AttachedDTO();
				attachedDTO.setMovieId(movieDTO.getId());
				attachedDTO.setPath(temp.getName());

				attachedList.add(attachedDTO);

			}

			ATTACH_SERVICE.insert(attachedList);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/movie/admin/list/1";
	}

	@GetMapping("printAll/{pageNo}")
	public String printAll(@PathVariable int pageNo, Model model) {

		int maxPage = MOVIE_SERVICE.selectMaxPage();
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
		
 
		

		model.addAttribute("list",MOVIE_SERVICE.selectAll(pageNo));
		 
	 
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/movie/printAll";
	}

	@GetMapping("admin/list/{pageNo}")
	public String liseAll(@PathVariable int pageNo, Model model) {

		int maxPage = MOVIE_SERVICE.selectMaxPage();
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

		model.addAttribute("list", MOVIE_SERVICE.selectAll(pageNo));

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/movie/movieListAdmin";
	}

	@GetMapping("printOne/{id}")
	public String printOne(@PathVariable int id, HttpSession session, Authentication authentication, Model model) {
 
		UserDTO logIn = (UserDTO) authentication.getPrincipal();

		MovieDTO movieDTO = MOVIE_SERVICE.selectOne(id);

		List<AttachedDTO> list = ATTACH_SERVICE.selectAll(id);
	    
		Double userAverage =REVIEW_SERVICE.userAverage(id);
		if (userAverage==null) {
			userAverage = 0.0;
		}
 
		
		Double criticAverage =REVIEW_SERVICE.criticAverage(id);
		if (criticAverage==null) {
			criticAverage = 0.0;
		}
		 List<ReviewDTO>  reviewCriticlist = REVIEW_SERVICE.selectAllByMovieId(id);
		 List<ReviewDTO>  reviewUserlist = REVIEW_SERVICE.selectAllByUSER(id);  
	       
	     model.addAttribute("reviewlist", reviewCriticlist);
	    model.addAttribute("reviewUserlist", reviewUserlist);
		model.addAttribute("movieDTO", movieDTO);
		model.addAttribute("attachedList", list);
		model.addAttribute("criticAverage",criticAverage);
		model.addAttribute("userAverage",userAverage);
		model.addAttribute("logInId", logIn.getId());
      	return "/movie/printOne";
	}

	@GetMapping("/admin/update/{id}")
	public String MovieUpdate(@PathVariable int id, Model model, Authentication auth) {

		UserDTO logIn = (UserDTO) auth.getPrincipal();

		MovieDTO movieDTO = MOVIE_SERVICE.selectOne(id);

		if (movieDTO == null) {
			return "redirect:/movie/list/admin/1";
		}

		List<AttachedDTO> list = ATTACH_SERVICE.selectAll(id);

		model.addAttribute("movieDTO", movieDTO);
		model.addAttribute("attachedList", list);
		return "/movie/update";

	}

	@PostMapping("/admin/update/{id}")
	public String Movieupdate(@PathVariable int id, MovieDTO movieDTO, HttpSession session,
			@RequestParam("file") List<MultipartFile> files) {
	 
		List<AttachedDTO> list = ATTACH_SERVICE.selectAll(id);
 
		// @RequestParam("file") List<MultipartFile> files가 있는 경우만 아래 코드 실행
		if (files.get(0).getSize() != 0 && !list.isEmpty()) {
            //files.get(0).getSize() != 0 이게 업로드파일있고 !list.isEmpty() 기존파일이 잇을때 
			// 즉, 기존파일이 존재하고 신규업로드 파일이 있을때 삭제 
			// 기존파일삭제
			ATTACH_SERVICE.delete(movieDTO.getId());

		}
		
		if (files.get(0).getSize() != 0) {
			  //신규파일등록 
			  String uploadPath = session.getServletContext().getRealPath("/") +"upload/" + movieDTO.getId();
			  
			  File dir = new File(uploadPath); if (!dir.exists()) { dir.mkdirs(); }
			  
			  ArrayList<AttachedDTO> attachedList = new ArrayList<>();
			  
			  try { for (MultipartFile file : files) { File temp = new File(uploadPath,
			  file.getOriginalFilename()); file.transferTo(temp);
			  
			  AttachedDTO attachedDTO = new AttachedDTO();
			  attachedDTO.setMovieId(movieDTO.getId());
			  attachedDTO.setPath(temp.getName());
			  
			  attachedList.add(attachedDTO);
			  
			  }
			  
			  ATTACH_SERVICE.insert(attachedList); } 
			  catch (IllegalStateException |
			  IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 
		}}

		movieDTO.setId(id);
		MOVIE_SERVICE.update(movieDTO);

		return "redirect:/movie/admin/printOne/" + id;
	}

	@GetMapping("admin/printOne/{id}")
	public String AdminprintOne(@PathVariable int id, HttpSession session, Authentication authentication, Model model) {

		UserDTO logIn = (UserDTO) authentication.getPrincipal();

		MovieDTO movieDTO = MOVIE_SERVICE.selectOne(id);
 
		List<AttachedDTO> list = ATTACH_SERVICE.selectAll(id);
		
		
		model.addAttribute("movieDTO", movieDTO);
		model.addAttribute("attachedList", list);
		
		model.addAttribute("logInId", logIn.getId());

		return "/movie/adminPrintOne";
	}
	
	
	
	
	@GetMapping("admin/delete/{id}")
	public String delete(@PathVariable int id, Authentication auth){
		UserDTO logIn = (UserDTO) auth.getPrincipal();
		
		MovieDTO movieDTO = MOVIE_SERVICE.selectOne(id);
		if(movieDTO==null) {
			return "redirect:/movie/admin.list/1";
		}
		
		MOVIE_SERVICE.delete(id);
		
		return "redirect:/movie/admin/list/1";
		
		
		
	}
	// 여러번 커밋테스트+두번째
	/*
	 * <<<<<<< HEAD //민영수정 ======= //윤지테스트 + 윤지수정 >>>>>>>
	 * f5f206da4ef5a230b7f6d1d597498acfab2353a6
	 */
	
	
}