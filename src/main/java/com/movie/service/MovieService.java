package com.movie.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.model.MovieDTO;

@Repository
public class MovieService {

	private final String NAMESPACE = "com.movie.mapper.MovieMapper";
	private final SqlSession SESSION;
	private final int PAGE_SIZE = 6;

	@Autowired
	public MovieService(SqlSession session) {
		this.SESSION = session;
	}

	public void insert(MovieDTO movieDTO) {
		SESSION.insert(NAMESPACE + ".insert", movieDTO);
	}

	public List<MovieDTO> selectAll(int pageNo) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("size", PAGE_SIZE);
		paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);

		return SESSION.selectList(NAMESPACE + ".selectAll", paramMap);
	}

	public MovieDTO selectOne(int id) {
		return SESSION.selectOne(NAMESPACE + ".selectOne", id);
	}

	public void update(MovieDTO movieDTO) {
		SESSION.update(NAMESPACE + ".update", movieDTO);
	}

	public void delete(int id) {
		SESSION.delete(NAMESPACE + ".delete", id);

	}

	public int selectMaxPage() {
		int total = SESSION.selectOne(NAMESPACE + ".count");
		int maxPage = total / PAGE_SIZE;
		if (total % PAGE_SIZE != 0) {
			maxPage++;
		}

		return maxPage;
	}
}
