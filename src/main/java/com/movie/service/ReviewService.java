package com.movie.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.model.ReviewDTO;

@Repository
public class ReviewService {
	private final String NAMESPACE = "com.movie.mapper.ReviewMapper";
	private final SqlSession SESSION;

	@Autowired
	public ReviewService(SqlSession session) {
		this.SESSION = session;
	}

	// 해당 영화의 전체 댓글 조회
	public List<ReviewDTO> selectAllByMovieId(int movieId) {
		return SESSION.selectList(NAMESPACE + ".selectAllByMovieId", movieId);
	}
	
	public List<ReviewDTO> selectAllByUSER(int movieId) {
		return SESSION.selectList(NAMESPACE + ".selectAllByUSER", movieId);
	}
	// 영화리뷰 추가
	public void insert(ReviewDTO reviewDTO) {
		SESSION.insert(NAMESPACE + ".insert", reviewDTO);
	}

	// 영화리뷰 수정
	public void update(ReviewDTO reviewDTO) {
		SESSION.update(NAMESPACE + ".update", reviewDTO);
	}

	// 영화리뷰 삭제
	public void delete(int id) {
		SESSION.delete(NAMESPACE + ".delete", id);

	}

	public ReviewDTO selectOne(int id) {
		return SESSION.selectOne(NAMESPACE + ".selectOne", id);
	}

	public Double userAverage(int id) {

		return SESSION.selectOne(NAMESPACE + ".userAverage", id);
	}

	public Double criticAverage(int id) {

		return SESSION.selectOne(NAMESPACE + ".criticAverage", id);
	}

}
