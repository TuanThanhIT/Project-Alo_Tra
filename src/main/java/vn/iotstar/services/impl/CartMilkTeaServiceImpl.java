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
	public BigDecimal calculateTotalPrice(int cartId) {
		List<CartMilkTea> cartMilkTeas = cartMilkTeaRepository.findByCartCartID(cartId);
		BigDecimal totalPrice = BigDecimal.ZERO; // Khởi tạo giá trị mặc định

		for (CartMilkTea cartMilkTea : cartMilkTeas) {
			BigDecimal price = cartMilkTea.getMilkTea().getPrice(); // Lấy giá
			BigDecimal quantity = BigDecimal.valueOf(cartMilkTea.getQuantityMilkTea()); // Chuyển số lượng thành
																						// BigDecimal
			totalPrice = totalPrice.add(price.multiply(quantity)); // Cộng giá vào tổng tiền
		}

		return totalPrice;
	}
	@Override
	public Optional<CartMilkTea> findById(int id)
	{
		return cartMilkTeaRepository.findById(id);
	}
	
	@Override
	public void deleteById(CartMilkTea cmilk)
	{
		cartMilkTeaRepository.delete(cmilk);
	}
}
