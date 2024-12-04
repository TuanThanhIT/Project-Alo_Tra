package vn.iotstar.repository.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import vn.iotstar.entity.MilkTea;

@Repository
public interface IMilkTeaRepository extends JpaRepository<MilkTea, Integer>{

}