package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import vn.iotstar.entity.Branch;

public interface IBranchService {

	void deleteAll();

	void delete(Branch entity);

	void deleteById(Integer id);

	long count();

	Optional<Branch> findById(Integer id);

	List<Branch> findAll();

	List<Branch> findAll(Sort sort);

	<S extends Branch> S save(S entity);

}
