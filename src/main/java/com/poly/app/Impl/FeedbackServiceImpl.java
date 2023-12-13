package com.poly.app.Impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Feedback;
import com.poly.app.repository.FeedbackRepository;
import com.poly.app.service.FeedbackService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

	private final  FeedbackRepository dao;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Feedback save(Feedback feedback) {
		return dao.save(feedback);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Feedback update(Feedback feedback) {
		return dao.save(feedback);
	}

	@Override
	public Optional<Feedback> findById(Integer id) {
		return dao.findById(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	@Override
	public List<Feedback> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Feedback> findByKeyWordLike(String name) {
		return dao.findByKeyWordLike(name);
	}

	@Override
	public List<Feedback> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Optional<List<Feedback>> findByReply(Boolean isactive) {
		return dao.findByReply(isactive);
	}


}
