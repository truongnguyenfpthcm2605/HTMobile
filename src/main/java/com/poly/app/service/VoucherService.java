package com.poly.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.poly.app.enity.Voucher;

public interface VoucherService {
		Voucher save(Voucher voucher);
		Voucher update(Voucher voucher);
		Optional<Voucher> findById(String id);
		void deleteById(String id);
		List<Voucher> findAll();
		Optional<Voucher> findByVoucher(String id, Date curent,Boolean active);
		Optional<List<Voucher>> findByNameLike(String name);
		List<Voucher> findAll(Sort sort);
		Optional<List<Voucher>> findByActive(Boolean isactive);
}
