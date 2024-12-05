package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.BranchMilkTea;

@Repository
public interface BranchMilkTeaRepository extends JpaRepository<BranchMilkTea, Integer> {

}
