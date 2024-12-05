package vn.iotstar.service.seller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.repository.seller.IMilkTeaTypeRepository;
import vn.iotstar.service.seller.IMilkTeaTypeService;

@Service
@Transactional
public class MilkTeaTypeServiceImpl implements IMilkTeaTypeService{
	@Autowired
	private IMilkTeaTypeRepository iMilkTeaTypeRepository;
	
	@Override
	public List<MilkTeaType> findAll() {
		return iMilkTeaTypeRepository.findAll();
	}

	@Override
	public Optional<MilkTeaType> findById(Integer id) {
		return iMilkTeaTypeRepository.findById(id);
	}
	
	

}
