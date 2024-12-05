package vn.iotstar.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Branch;
import vn.iotstar.repository.seller.IBranchRepository;
import vn.iotstar.services.IBranchService;

@Service
public class BranchSeriveImpl implements IBranchService {
	@Autowired
    IBranchRepository branchRepository;
	@Override
	public <S extends Branch> S save(S entity) {
		return branchRepository.save(entity);
	}

	@Override
	public List<Branch> findAll(Sort sort) {
		return branchRepository.findAll(sort);
	}

	@Override
	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

	@Override
	public Optional<Branch> findById(Integer id) {
		return branchRepository.findById(id);
	}

	@Override
	public long count() {
		return branchRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		branchRepository.deleteById(id);
	}

	
	@Override
	public void delete(Branch entity) {
		branchRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		branchRepository.deleteAll();
	}

	

	public BranchSeriveImpl(IBranchRepository branchRepository) {
		super();
		this.branchRepository = branchRepository;
	}
	
}
