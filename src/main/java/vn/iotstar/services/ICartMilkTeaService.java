package vn.iotstar.services;

import java.math.BigDecimal;
import java.util.Optional;

import vn.iotstar.entity.CartMilkTea;

public interface ICartMilkTeaService {


	Optional<CartMilkTea> findById(int id);

	void deleteById(int id);

	

}
