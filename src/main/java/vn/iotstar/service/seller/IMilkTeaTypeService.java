package vn.iotstar.service.seller;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.MilkTeaType;

public interface IMilkTeaTypeService {
	public List<MilkTeaType> findAll();

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<MilkTeaType> findById(Integer id);
}
