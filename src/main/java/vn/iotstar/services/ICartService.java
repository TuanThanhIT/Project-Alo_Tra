package vn.iotstar.services;

import java.util.Optional;

import vn.iotstar.entity.Cart;

public interface ICartService {

	Optional<Cart> findByUserId(int id);

	<S extends Cart> S save(S entity);

	

}
