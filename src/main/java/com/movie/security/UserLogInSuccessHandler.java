package com.movie.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class UserLogInSuccessHandler implements AuthenticationSuccessHandler {

	@Override // 로그인이 성공하면 해야할 작업들을 여기에 정의함
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess= "+authentication);
		response.sendRedirect("/movie/printAll/1"); // return타입이 void이므로 response.sendRedirect 사용
	}

}
