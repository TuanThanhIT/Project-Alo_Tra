package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
