package vn.iotstar.service.impl.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Discount;
import vn.iotstar.repository.admin.DiscountRepository;
import vn.iotstar.service.admin.IDiscount;

@Service
public class DiscountImpl implements IDiscount{

	public <S extends Discount> S save(S entity) {
		return discountRepository.save(entity);
	}

	public List<Discount> findAll(Sort sort) {
		return discountRepository.findAll(sort);
	}

	public Page<Discount> findAll(Pageable pageable) {
		return discountRepository.findAll(pageable);
	}

	public List<Discount> findAll() {
		return discountRepository.findAll();
	}

	public List<Discount> findAllById(Iterable<Integer> ids) {
		return discountRepository.findAllById(ids);
	}

	public long count() {
		return discountRepository.count();
	}

	public void deleteById(Integer id) {
		discountRepository.deleteById(id);
	}

	public void delete(Discount entity) {
		discountRepository.delete(entity);
	}

	public void deleteAll() {
		discountRepository.deleteAll();
	}

	@Autowired
	DiscountRepository discountRepository;

	public DiscountImpl(DiscountRepository discountRepository) {
		super();
		this.discountRepository = discountRepository;
	}

	

	@Override
	public Optional<Discount> findById(Integer id) {
		return discountRepository.findById(id);
	}

	
	
	
}
