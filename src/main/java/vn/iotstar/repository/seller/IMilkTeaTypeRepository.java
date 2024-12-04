package vn.iotstar.repository.seller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.entity.MilkTeaType;

public interface IMilkTeaTypeRepository extends JpaRepository<MilkTeaType, Integer>{
	Optional<MilkTeaType> findByMilkTeaTypeName(String milkTeaTypeName);
}
