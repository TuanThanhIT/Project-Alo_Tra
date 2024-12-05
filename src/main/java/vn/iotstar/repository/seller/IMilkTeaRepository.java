package vn.iotstar.repository.seller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import vn.iotstar.entity.MilkTea;

@Repository
public interface IMilkTeaRepository extends JpaRepository<MilkTea, Integer>{
	Page<MilkTea> findByMilkTeaType_MilkTeaTypeID(int milkTeaTypeID, Pageable pageable);
}
