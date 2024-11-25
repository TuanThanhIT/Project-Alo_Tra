package vn.iotstar.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.MilkTea;

@Repository
public interface MilkTeaRepository extends JpaRepository<MilkTea, Integer> {
	@Query("SELECT mt FROM MilkTea mt WHERE mt.milkTeaName LIKE %:keyword%")
	List<MilkTea> findByMilkTeaNameContaining(@Param("keyword") String keyword);
}
