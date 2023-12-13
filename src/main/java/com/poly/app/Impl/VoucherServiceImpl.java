package com.poly.app.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.app.enity.Voucher;
import com.poly.app.repository.VoucherRepository;
import com.poly.app.service.VoucherService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

	private final VoucherRepository dao;
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Voucher save(Voucher voucher) {
		return dao.save(voucher);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public Voucher update(Voucher voucher) {
		return dao.save(voucher);
	}

	@Override
	public Optional<Voucher> findById(String id) {
		return dao.findById(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public List<Voucher> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Voucher> findByVoucher(String id, Date curent, Boolean active) {
		return dao.findByVoucher(id, curent, active);
	}

	@Override
	public Optional<List<Voucher>> findByNameLike(String name) {
		return dao.findByNameLike(name);
	}

	@Override
	public List<Voucher> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Optional<List<Voucher>> findByActive(Boolean isactive) {
		return dao.findByActive(isactive);
	}
		
}
