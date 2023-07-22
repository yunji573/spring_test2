package com.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.movie.model.UserDTO;

//스프링이 관리할수있도록 @Repository 등록해줘야함
@Repository
public class UserService implements UserDetailsService {
	private final String NAMESPACE = "com.movie.mapper.UserMapper";
	private final SqlSession SESSION;
	private final int PAGE_SIZE = 7;
	// SqlSession 우리가 직접 초기화하지 않고, root-context.xml에 이미 정의를 해놓아서...

	@Autowired // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
	public UserService(SqlSession sqlSession) {
		this.SESSION = sqlSession;
	}

	public UserDTO auth(UserDTO userDTO) {
		return SESSION.selectOne(NAMESPACE + ".auth", userDTO);

	}

	public boolean register(UserDTO userDTO) {
		if (SESSION.selectOne(NAMESPACE + ".auth", userDTO) == null) {
			SESSION.insert(NAMESPACE + ".register", userDTO);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);

		return SESSION.selectOne(NAMESPACE + ".selectOneByUsername", userDTO);

	}

	public String finfRole(String username) {
		return SESSION.selectOne(NAMESPACE + ".selectOne", username);

	}

	public List<UserDTO> selectAll() {
		return SESSION.selectList(NAMESPACE + ".selectAll");
	}

	public void update(UserDTO userDTO) {
		SESSION.update(NAMESPACE + ".update", userDTO);
	}

	public void roleUpdate(UserDTO userDTO) {
		SESSION.update(NAMESPACE + ".roleUpdate", userDTO);
	}

	public List<UserDTO> selectAll2(int pageNo) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("size", PAGE_SIZE);
		paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);

		return SESSION.selectList(NAMESPACE + ".selectAll2", paramMap);
	}

	public int selectMaxPage() {
		int total = SESSION.selectOne(NAMESPACE + ".count");
		int maxPage = total / PAGE_SIZE;
		if (total % PAGE_SIZE != 0) {
			maxPage++;
		}

		System.out.println(maxPage);
		return maxPage;
	}

}
