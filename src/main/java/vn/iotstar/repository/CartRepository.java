package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
	Optional<Cart> findByUserUserID(int userID);

}
