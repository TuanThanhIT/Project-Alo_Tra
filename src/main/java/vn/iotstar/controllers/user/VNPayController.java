package vn.iotstar.controllers.user;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.User;
import vn.iotstar.repository.OrderRepository;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.IMilkTeaService;
import vn.iotstar.services.paymethod.VNPayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VNPayController {
	@Autowired
	private VNPayService vnPayService;
	@Autowired
	private OrderRepository orderService;
	@Autowired
	private ICartService cartService;



	@GetMapping("/vnpay-payment-return")
	public String paymentCompleted(HttpServletRequest request, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int paymentStatus = vnPayService.orderReturn(request);
		Order order = (Order) session.getAttribute("checkingOutOrder");

		Integer check = (Integer) session.getAttribute("single");
		if (paymentStatus == 1) {

			orderService.save(order);

			cartService.deleteAllItem(user.getUserID());

			return "redirect:/User/Order";
		} else {
			return "redirect:/User";
		}
	}

	@PostMapping("/submitOrder")
	public String submidOrder(@RequestParam("amount") int orderTotal, @RequestParam("orderInfo") String orderInfo,
			HttpServletRequest request) {
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);
		return "redirect:" + vnpayUrl;
	}

}