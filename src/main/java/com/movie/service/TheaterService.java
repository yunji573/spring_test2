package com.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.model.TheaterDTO;

@Repository
public class TheaterService {
	private final String NAMESPACE = "com.movie.mapper.TheaterMapper";
	private final SqlSession SESSION;
	private final int PAGE_SIZE = 6;

	@Autowired
	public TheaterService(SqlSession session) {
		this.SESSION = session;
	}

	public void insert(TheaterDTO theaterDTO) {
		SESSION.insert(NAMESPACE + ".insert", theaterDTO);
	}

	 


	public TheaterDTO selectOne(int id) {
		return SESSION.selectOne(NAMESPACE + ".selectOne", id);
	}

	public void update(TheaterDTO theaterDTO) {
		SESSION.update(NAMESPACE + ".update", theaterDTO);
	}

	public void delete(int id) {
		SESSION.delete(NAMESPACE + ".delete", id);

	}

	
	
	public List<TheaterDTO> selectAll(int pageNo) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("size", PAGE_SIZE);
		paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);

		return SESSION.selectList(NAMESPACE + ".selectAll", paramMap);
	}
	
	public int selectMaxPage() {
		int total = SESSION.selectOne(NAMESPACE + ".count");
		int maxPage = total / PAGE_SIZE;
		if (total % PAGE_SIZE != 0) {
			maxPage++;
		}

		return maxPage;
	}
	
	public Map<String, String[]> selectbytheater(int id) {
	    List<TheaterDTO> list = SESSION.selectList(NAMESPACE+".selectbytheater", id);
	    
	    Map<String, List<TheaterDTO>> grouped = list.stream()
	        .collect(Collectors.groupingBy(TheaterDTO::getTitle));
	    
	    Map<String, String[]> multiMap = new HashMap<>();
	    for (Map.Entry<String, List<TheaterDTO>> entry : grouped.entrySet()) {
	    	
	        String[] times = entry.getValue().stream()
	            .map(TheaterDTO::getTime)
	            .toArray(String[]::new);
	        
	        multiMap.put(entry.getKey(), times);
	    }
	    return multiMap;
	}
}
