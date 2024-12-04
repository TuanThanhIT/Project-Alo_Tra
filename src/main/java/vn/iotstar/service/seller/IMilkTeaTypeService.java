package vn.iotstar.service.seller;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.MilkTeaType;

public interface IMilkTeaTypeService {
	public List<MilkTeaType> findAll();
	Optional<MilkTeaType> findById(Integer id);
	MilkTeaType findByName(String name);
}
