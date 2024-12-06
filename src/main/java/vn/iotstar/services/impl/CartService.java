package vn.iotstar.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Cart;
import vn.iotstar.entity.User;
import vn.iotstar.repository.CartRepository;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.services.ICartService;

@Service
public class CartService implements ICartService{
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Optional<Cart> findByUserId(int id) {
		return cartRepo.findByUserUserID(id);
	}
	
	@Override
	public <S extends Cart> S save(S entity)
	{
		return cartRepo.save(entity);
	}

	@Override
	public Long getTotalProductCount() {
		return cartRepo.getTotalProductCount();
	}
}	
