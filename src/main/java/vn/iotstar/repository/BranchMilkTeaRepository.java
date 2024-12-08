package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Branch;
import vn.iotstar.entity.BranchMilkTea;

@Repository
public interface BranchMilkTeaRepository extends JpaRepository<BranchMilkTea, Integer> {
	@Query("SELECT bm FROM BranchMilkTea bm WHERE bm.branch = :branch")
	Page<BranchMilkTea> findByBranch(@Param("branch") Branch branch, Pageable pageable);
}
