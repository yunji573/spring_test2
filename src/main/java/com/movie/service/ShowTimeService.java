package com.movie.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.model.MovieDTO;
import com.movie.model.ShowTimeDTO;

@Repository
public class ShowTimeService {
	private final String NAMESPACE = "com.movie.mapper.ShowTimeMapper";
	private final SqlSession SESSION;
	private final int PAGE_SIZE = 6;

	@Autowired
	public ShowTimeService(SqlSession session) {
		this.SESSION = session;
	}

	public void insert(List<ShowTimeDTO> list) {
		SESSION.insert(NAMESPACE + ".insert", list);
	}

	public void update(ShowTimeDTO showTimeDTO) {
		SESSION.update(NAMESPACE + ".update", showTimeDTO);
	}

	public List<ShowTimeDTO> selectAll() {
		return SESSION.selectList(NAMESPACE + ".selectAll");
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

	public List<ShowTimeDTO> selectAll2(int pageNo) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("size", PAGE_SIZE);
		paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);

		return SESSION.selectList(NAMESPACE + ".selectAll2", paramMap);
	}

	public ShowTimeDTO selectOne(int id) {
		return SESSION.selectOne(NAMESPACE + ".selectOne", id);
	}

}
