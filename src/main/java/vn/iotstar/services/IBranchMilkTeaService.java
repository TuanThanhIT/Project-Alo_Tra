package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.BranchMilkTea;

public interface IBranchMilkTeaService {
	public List<BranchMilkTea> findAll();

	public Optional<BranchMilkTea> findById(int id);

	public BranchMilkTea save(BranchMilkTea branchmilkTea);// thêm mới hoặc cập nhật

	public void deleteById(int id);
}
