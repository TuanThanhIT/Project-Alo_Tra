package vn.iotstar.service.seller;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.Branch;

public interface IBranchService {
	public List<Branch> findAll();
	public Optional<Branch> findById(int id);
	public Branch save(Branch branch);// thêm mới hoặc cập nhật
	public void deleteById(int id);
}
