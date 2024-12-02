package vn.iotstar.repository.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.BranchMilkTea;

@Repository
public interface IBranchMilkTeaRepository extends JpaRepository<BranchMilkTea, Integer>{

}
