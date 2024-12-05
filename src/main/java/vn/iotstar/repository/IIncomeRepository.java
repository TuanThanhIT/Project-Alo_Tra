package vn.iotstar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.entity.Income;

public interface IIncomeRepository extends JpaRepository<Income, Integer>{
	List<Income> findByBranch_BranchID(int branchId);
}
