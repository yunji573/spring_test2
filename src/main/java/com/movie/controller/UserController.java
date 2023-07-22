package com.movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.movie.model.MovieDTO;
import com.movie.model.UserDTO;
import com.movie.service.UserService;
import com.google.gson.JsonObject;

@RequestMapping("/user/")
@Controller
public class UserController {

	private final UserService USER_SERVICE;
	private final PasswordEncoder ENCODER;

	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.USER_SERVICE = userService;
		this.ENCODER = passwordEncoder;
	}
 

	@GetMapping("register")
	public String register() {
		return "user/register";
	}

	/* @PostMapping("register") */
	public void register(UserDTO attempt) {

		attempt.setPassword(encryptPW(attempt.getPassword()));

		USER_SERVICE.register(attempt);

		 
	}

	@ResponseBody
	@PostMapping(value="validate",produces="application/text; charset=utf8")
	public String validateUsername(UserDTO userDTO) {
		System.out.println(userDTO);
		
		JsonObject jsonObject = new JsonObject();
		if (USER_SERVICE.loadUserByUsername(userDTO.getUsername()) == null) {
			register(userDTO);
	
			jsonObject.addProperty("result", "success");
			jsonObject.addProperty("message", "!!!회원가입성공!!!");
		} else {
			jsonObject.addProperty("result", "fail");
			jsonObject.addProperty("message", "이미존재하는 회원아이디 입니다.");
		}
		return jsonObject.toString();

	}

	/* @GetMapping("encrypt") */
	private String encryptPW(String password) {
		
		return ENCODER.encode(password);

	}
	
	
	@GetMapping("admin/list/{pageNo}")
	public String liseAll(@PathVariable int pageNo, Model model) {

		int maxPage = USER_SERVICE.selectMaxPage();
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

		model.addAttribute("list", USER_SERVICE.selectAll2(pageNo));
		 

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);

		return "/user/userList";
	}

	@ResponseBody
	@PostMapping(value="admin/update/{id}",produces="application/text; charset=utf8")
	public String roleUpdate(@PathVariable int id, UserDTO userDTO) {
		
		userDTO.setId(id);
        //role업데이트
		USER_SERVICE.roleUpdate(userDTO);

        //authorities set
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();//초기화시켜서 만듬
		authorities.add(new SimpleGrantedAuthority(userDTO.getRole()));
		userDTO.setAuthorities(authorities);
		
  
		JsonObject jsonObject = new JsonObject();
	
		 
			jsonObject.addProperty("result", "success");
			jsonObject.addProperty("message", "!!!ROLE변경성공!!!");
		
		return jsonObject.toString();

		}
	
	 
}
