package vn.iotstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.CartMilkTea;

@Repository
public interface CartMilkTeaRepository extends JpaRepository<CartMilkTea, Integer>{
	List<CartMilkTea> findByCartCartID(int cartID);

	
	Optional<CartMilkTea> findById(int id);
}
