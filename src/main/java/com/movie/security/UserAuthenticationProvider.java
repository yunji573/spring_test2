package com.movie.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.model.UserDTO;
import com.movie.service.UserService;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {

	private final UserService USER_SERVICE;
	private final BCryptPasswordEncoder PASSWORD_ENCODER;

	@Autowired
	public UserAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.USER_SERVICE = userService;
		this.PASSWORD_ENCODER = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

 
		UserDTO userDTO = (UserDTO) USER_SERVICE.loadUserByUsername(username);


		// userDTO.getPassword()가 암호화된 상태여야 비교가 가능하다...
		if (userDTO != null && PASSWORD_ENCODER.matches(password, userDTO.getPassword())) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();//초기화시켜서 만듬
			authorities.add(new SimpleGrantedAuthority(userDTO.getRole()));

			for (GrantedAuthority authority : authorities) {
				System.out.println(authority.getAuthority());
			}
            
			userDTO.setAuthorities(authorities);

			return new UsernamePasswordAuthenticationToken(userDTO, null, userDTO.getAuthorities());
		}

		return null;
	}// 로그인을 실제담당함

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
