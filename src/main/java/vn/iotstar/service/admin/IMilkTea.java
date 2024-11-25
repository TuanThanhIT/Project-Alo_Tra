package vn.iotstar.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.MilkTeaType;

public interface IMilkTea {


	void delete(MilkTea entity);

	void deleteById(Integer id);

	long count();

	Optional<MilkTea> findById(Integer id);

	List<MilkTea> findAll();

	Page<MilkTea> findAll(Pageable pageable);

	<S extends MilkTea> S save(S entity);
	
	List<MilkTea> findByMilkTeaNameContaining(String keyword);


}
