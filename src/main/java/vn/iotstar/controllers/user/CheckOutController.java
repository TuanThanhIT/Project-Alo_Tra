
package vn.iotstar.controllers.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartMilkTea;
import vn.iotstar.entity.Delivery;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.Pays;
import vn.iotstar.entity.User;
import vn.iotstar.enums.OrderStatus;
import vn.iotstar.services.ICartMilkTeaService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.IDeliveryService;
import vn.iotstar.services.IMilkTeaService;
import vn.iotstar.services.IOrderService;
import vn.iotstar.services.ISizeService;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {

	@Autowired
	private IMilkTeaService milkTeaServ;
	@Autowired
	private ICartService cartServ;
	@Autowired
	private ICartMilkTeaService cmilkTeaServ;

	@Autowired
	private ISizeService sizeServ;

	@Autowired
	private IOrderService orderServ;

	@Autowired
	private IDeliveryService deliServ;

	@GetMapping("/preview")
	public String getCheckOut(HttpSession session, Model model) {
		// Lấy thông tin người dùng từ session
		User user = (User) session.getAttribute("account");
		// Tìm giỏ hàng theo User ID
		Cart cart = cartServ.findByUserId(user.getUserID()).orElse(null);

		List<CartMilkTea> cmilkTea = cart.getMilkTeas();

		model.addAttribute("listcart", cmilkTea);

		// Tính tổng giá tiền và thêm vào model
		BigDecimal totalPrice = cmilkTeaServ.calculateTotalPrice(cart.getCartID());
		model.addAttribute("total", totalPrice);

		List<Delivery> listDeli = deliServ.findAll();

		model.addAttribute("listdeli", listDeli);

		return "user/checkout";
	}

	@PostMapping("/checkout-COD")
	public String payNow(HttpSession session, Model model, @RequestParam("address") String address,
			@RequestParam(required = false) Integer deliveryId) {
		// Lấy thông tin người dùng từ session
		User user = (User) session.getAttribute("account");
		Cart cart = cartServ.findByUserId(user.getUserID()).orElse(null);
		Order order = new Order();
		order.setCart(cart);
		order.setShipAddress(address);
		order.setUser(user);
		order.setStatus(OrderStatus.PENDING);

		Pays payment = new Pays();
		payment.setPayMethod("COD");
		order.setPayment(payment);

		cartServ.deleteAllItem(user.getUserID());
		orderServ.save(order);

		return "redirect:/user/home";
	}
	
	@PostMapping("/checkout-by-VNPay")
	public String payVN(HttpSession session, Model model, @RequestParam("address") String address,
			@RequestParam(required = false) Integer deliveryId) {
		return null;
	}
/*
	@GetMapping("/checkout/product/{milkTeaID}")
	public String checkoutSingleProduct(Model model, HttpSession session, @PathVariable("milkTeaID") int milkTeaID, @RequestParam int quantity, @RequestParam String size ) {
		// Lấy thông tin người dùng từ session
		User user = (User) session.getAttribute("account");
		MilkTea mTea = milkTeaServ.findById(milkTeaID).get();
		
		List<Delivery> listDeli = deliServ.findAll();
		
		model.addAttribute("product",mTea);
		model.addAttribute("listDeli",listDeli);
		return null;
	}*/
}
