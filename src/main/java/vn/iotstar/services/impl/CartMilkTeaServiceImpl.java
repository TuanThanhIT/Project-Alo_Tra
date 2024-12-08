package vn.iotstar.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.CartMilkTea;
import vn.iotstar.repository.CartMilkTeaRepository;
import vn.iotstar.services.ICartMilkTeaService;

@Service
public class CartMilkTeaServiceImpl implements ICartMilkTeaService {
	@Autowired
	private CartMilkTeaRepository cartMilkTeaRepository;

	

	@Override
	public Optional<CartMilkTea> findById(int id) {
		return cartMilkTeaRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		cartMilkTeaRepository.deleteById(id);
	}

}
