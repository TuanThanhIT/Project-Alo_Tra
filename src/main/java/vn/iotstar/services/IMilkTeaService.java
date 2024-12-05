package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iotstar.entity.MilkTea;

public interface IMilkTeaService {

	void delete(MilkTea entity);

	Optional<MilkTea> findById(Integer id);


	List<MilkTea> findAll(Sort sort);

	<S extends MilkTea> S save(S entity);

	List<MilkTea> findAll();

	List<MilkTea> findByMilkTeaType_MilkTeaTypeID(int milkTeaTypeID);

	List<MilkTea> findByMilkTeaIDNot(int milkTeaID);

	Page<MilkTea> findAll(Integer pageNo);

	Page<MilkTea> findByMilkTeaType_MilkTeaTypeID(int milkTeaTypeID, Integer pageNo);
	
	List<MilkTea> searchMilkTea(String keyword);
	
	Page<MilkTea> searchMilkTea(String keyword, Integer pageNo);

	Page<MilkTea> searchMilkTeaByType(String keyword, int milkTeaTypeID, Integer pageNo);

	List<MilkTea> searchMilkTeaByType(int milkTeaTypeID, String keyword);
	
	
	
	
	
	
	
}
