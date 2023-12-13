package com.poly.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.app.enity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	@Query("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password and u.active =1")
	Users checkLogin(@Param("email") String email, @Param("password") String password);

	@Query("SELECT u FROM Users u WHERE u.email = :email ")
	Users getByEmail(@Param("email") String email);

	@Query("select count(o) from Users o")
	Integer getToTalNewUser();
	
	@Query("select o from Users o where o.fullname Like :keyword or o.email Like :keyword")
	List<Users> findByKeywords(@Param("keyword") String keyword);
	Optional<List<Users>> findByActive(Boolean active);
	
	@Query("select o from  Users o where  o.active = :isactive")
	List<Users> findBySortAndActive(@Param("isactive") Boolean isactive, Sort sort);
}
