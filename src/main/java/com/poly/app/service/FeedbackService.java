package com.poly.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.poly.app.enity.Feedback;
public interface FeedbackService {
	Feedback save(Feedback voucher);
	Feedback update(Feedback voucher);
	Optional<Feedback> findById(Integer id);
	void deleteById(Integer id);
	List<Feedback> findAll();
	List<Feedback> findAll(Sort sort);
	List<Feedback> findByKeyWordLike(String name);
	Optional<List<Feedback>> findByReply(Boolean isactive);

}
