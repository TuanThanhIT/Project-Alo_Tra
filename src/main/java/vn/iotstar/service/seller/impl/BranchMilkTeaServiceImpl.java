package vn.iotstar.service.seller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.entity.BranchMilkTea;
import vn.iotstar.repository.seller.IBranchMilkTeaRepository;
import vn.iotstar.service.seller.IBranchMilkTeaService;

@Service
@Transactional
public class BranchMilkTeaServiceImpl implements IBranchMilkTeaService{
	@Autowired
	private IBranchMilkTeaRepository iBranchMilkTeaRepository;

	@Override
	public List<BranchMilkTea> findAll() {
		return iBranchMilkTeaRepository.findAll();
	}

	@Override
	public Optional<BranchMilkTea> findById(int id) {
		return iBranchMilkTeaRepository.findById(id);
	}

	@Override
	public BranchMilkTea save(BranchMilkTea branchmilkTea) {
		return iBranchMilkTeaRepository.save(branchmilkTea);
	}

	@Override
	public void deleteById(int id) {
		iBranchMilkTeaRepository.deleteById(id);
		
	}
	
	
}
