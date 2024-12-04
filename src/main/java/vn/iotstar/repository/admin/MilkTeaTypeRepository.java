package vn.iotstar.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.MilkTeaType;

@Repository
public interface MilkTeaTypeRepository extends JpaRepository<MilkTeaType, Integer> {

	
}