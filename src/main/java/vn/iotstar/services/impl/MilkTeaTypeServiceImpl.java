package vn.iotstar.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.repository.MilkTeaTypeRepository;
import vn.iotstar.services.IMilkTeaTypeService;

@Service
public class MilkTeaTypeServiceImpl implements IMilkTeaTypeService{
	
	@Autowired
	private MilkTeaTypeRepository milkteaType;

	@Override
	public List<MilkTeaType> findAll() {
		return milkteaType.findAll();
	}
	
	
}
