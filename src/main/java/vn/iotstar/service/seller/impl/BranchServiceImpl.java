package vn.iotstar.service.seller.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.iotstar.entity.Branch;
import vn.iotstar.service.seller.IBranchService;
import vn.iotstar.repository.seller.IBranchRepository;

@Service
@Transactional
public class BranchServiceImpl implements IBranchService{
	@Autowired
	private IBranchRepository iBranchRepository;
	
	@Override
	public List<Branch> findAll() {
		return iBranchRepository.findAll();
	}

	@Override
	public Optional<Branch> findById(int id) {
		return iBranchRepository.findById(id);
	}

	@Override
	public Branch save(Branch branch) {
		return iBranchRepository.save(branch);
	}

	@Override
	public void deleteById(int id) {
		iBranchRepository.deleteById(id);
		
	}
}
