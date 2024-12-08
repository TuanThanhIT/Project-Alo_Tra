package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.Branch;
import vn.iotstar.entity.BranchMilkTea;

public interface IBranchMilkTeaService {
	public List<BranchMilkTea> findAll();

	public Optional<BranchMilkTea> findById(int id);

	public BranchMilkTea save(BranchMilkTea branchmilkTea);// thêm mới hoặc cập nhật

	public void deleteById(int id);

	Page<BranchMilkTea> getBranchMilkTeaByBranch(Branch branch, int pageNo);
}
