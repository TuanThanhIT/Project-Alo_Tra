package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iotstar.entity.Branch;
import vn.iotstar.entity.MilkTea;

public interface IBranchService {

	Optional<Branch> findById(Integer id);

	List<Branch> findAll();

	List<MilkTea> findMilkTeaByBranchID(int branchID);

	List<Branch> findByAddressContaining(String cityName);

	Page<Branch> searchBranch(String keyword, Integer pageNo);

	List<Branch> searchBranch(String keyword);

	Page<Branch> findAll(Integer pageNo);

	Page<Branch> findByAddressContaining(String cityName, Integer pageNo);

	Page<Branch> searchBranchInCity(String keyword, String cityId, Integer pageNo);

}
