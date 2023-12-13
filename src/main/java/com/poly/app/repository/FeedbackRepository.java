package com.poly.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.app.enity.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	@Query("select o from Feedback o where o.users.fullname like ?1 ")
	List<Feedback> findByKeyWordLike(String name);
	
	Optional<List<Feedback>> findByReply(Boolean isactive);
}
