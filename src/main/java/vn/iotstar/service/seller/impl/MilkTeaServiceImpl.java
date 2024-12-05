package vn.iotstar.service.seller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.repository.seller.IMilkTeaRepository;
import vn.iotstar.service.seller.IMilkTeaService;

@Service
@Transactional
public class MilkTeaServiceImpl implements IMilkTeaService{
	@Autowired
	private IMilkTeaRepository iMilkTeaRepository;
	
	@Override
	public List<MilkTea> findAll() {
		return iMilkTeaRepository.findAll();
	}

	@Override
	public Optional<MilkTea> findById(int id) {
		return iMilkTeaRepository.findById(id);
	}

	@Override
	public MilkTea save(MilkTea milkTea) {
		return iMilkTeaRepository.save(milkTea);
	}

	@Override
	public void deleteById(int id) {
		iMilkTeaRepository.deleteById(id);
		
	}
	
	@Override
	public Page<MilkTea> findAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo-1,2);
		return this.iMilkTeaRepository.findAll(pageable);
	}

	@Override
	public Page<MilkTea> findByMilkTeaType_MilkTeaTypeID(int typeMilkTeaID, Integer pageNo) {
	    Pageable pageable = PageRequest.of(pageNo-1, 2);
	    return this.iMilkTeaRepository.findByMilkTeaType_MilkTeaTypeID(typeMilkTeaID, pageable);
	}
}
