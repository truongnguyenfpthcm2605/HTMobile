package com.poly.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.app.enity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") Integer categoryId);
	
	 @Query("SELECT o FROM Product o WHERE o.title LIKE :keywords or o.category.title Like :keywords ")
	 Page<Product> findByKeywords(@Param("keywords") String  keywords,Pageable pageable);
	 
	 @Query("SELECT o.category.title, sum(o.price), count(o)"
		        + " FROM Product o "
		        + " GROUP BY o.category.title"
		        + " ORDER BY sum(o.price) DESC")
		List<Object[]> getInventoryByCategory();

	 
	 @Query("select count(o.quantitysold) from Product o")
	 Integer getTotalQuantitySold();
	 
	 @Query("SELECT o FROM Product o WHERE o.title LIKE :keywords or o.category.title Like :keywords ")
	 List<Product> findByKeywordsSearch(@Param("keywords") String key);

	 @Query("select o from Product o where MONTH(o.createday) = ?1")
	 Optional<List<Product>> findByProductMonth(Integer month);
	 
	 
	 @Query("select o from Product o where o.category.id = ?1")
	 List<Product> findBycategory(Integer id);
	 
	 @Query("SELECT p FROM Product p ORDER BY p.views DESC")
	    List<Product> findTop10ByViewsDesc();
	 
}
