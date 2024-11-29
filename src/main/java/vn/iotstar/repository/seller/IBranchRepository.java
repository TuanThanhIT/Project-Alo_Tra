package vn.iotstar.repository.seller;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Branch;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, Integer>{
	
}
