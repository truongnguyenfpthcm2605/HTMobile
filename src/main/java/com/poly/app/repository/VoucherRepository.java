package com.poly.app.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.app.enity.Voucher;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository  extends JpaRepository<Voucher,String >{
	
	@Query("select o from Voucher o where o.id=?1 and o.endday > ?2 and o.active = ?3 ")
	 Optional<Voucher> findByVoucher(String id, Date curent, Boolean active);
	
	Optional<List<Voucher>> findByNameLike(String name);
	Optional<List<Voucher>> findByActive(Boolean isactive);
	
}
