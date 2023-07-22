package com.movie.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.model.AttachedDTO;

@Repository
public class AttachedService {
	private final String NAMESPACE ="com.movie.mapper.AttachMapper";
	private final SqlSession SESSION;
	@Autowired
	public AttachedService(SqlSession session) {
		this.SESSION = session;	
	}
	
	
	public List<AttachedDTO> selectAll(int movieId){
		return SESSION.selectList(NAMESPACE+".selectAll",movieId);
	}
	
	public void insert(List<AttachedDTO> list) {
		SESSION.insert(NAMESPACE+".insert",list);
	}
	
	public void delete(int movieId) {
		SESSION.delete(NAMESPACE+".delete",movieId);
	}
}
