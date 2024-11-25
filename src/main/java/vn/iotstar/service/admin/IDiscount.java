package vn.iotstar.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iotstar.entity.Discount;


public interface IDiscount {

	void deleteAll();

	void delete(Discount entity);
	
	void deleteById(Integer id);

	long count();

	Optional<Discount> findById(Integer id);

	List<Discount> findAll();

	Page<Discount> findAll(Pageable pageable);

	List<Discount> findAll(Sort sort);

	<S extends Discount> S save(S entity);

	
}
