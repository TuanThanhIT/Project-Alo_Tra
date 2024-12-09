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
import vn.iotstar.services.IPayService;
import vn.iotstar.services.ISizeService;

@Controller
@RequestMapping("user/checkout")
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
	private IPayService paysServ;
	@Autowired
	private IDeliveryService deliServ;

	 @GetMapping("/preview")
	    public String getCheckOut(HttpSession session, Model model) {
	        // Lấy thông tin người dùng từ session
	        User user = (User) session.getAttribute("account");

	        // Tìm giỏ hàng theo User ID
	        Cart cart = cartServ.findByUserId1(user.getUserID()).orElse(null);
	        if (cart == null) {
	            // Nếu không tìm thấy giỏ hàng, trả về trang lỗi hoặc trang giỏ hàng
	            return "redirect:/cart";
	        }

	        List<CartMilkTea> cmilkTea = cart.getMilkTeas();
	        model.addAttribute("listcart", cmilkTea);

	        model.addAttribute("total", cart.getTotalCost());

	        // Lấy tất cả các phương thức giao hàng
	        List<Delivery> listDeli = deliServ.findAll();
	        model.addAttribute("listdeli", listDeli);
	        
	        System.out.println(listDeli);

	        return "user/checkout";
	    }

	 @PostMapping("/checkout-COD")
	 public String payNow(HttpSession session, Model model, 
	                      @RequestParam("address") String address,
	                      @RequestParam(required = false) Integer deliveryId) {
		 
		 System.out.println(deliveryId);
	     // Lấy thông tin người dùng từ session
	     User user = (User) session.getAttribute("account");
	     if (user == null) {
	         // Nếu người dùng chưa đăng nhập, chuyển hướng về trang đăng nhập
	         return "redirect:/login";
	     }

	     // Tìm giỏ hàng của người dùng
	     Cart cart = cartServ.findByUserId1(user.getUserID()).orElse(null);
	     if (cart == null) {
	         // Nếu không tìm thấy giỏ hàng, trả về trang lỗi hoặc xử lý theo yêu cầu
	         model.addAttribute("error", "Giỏ hàng không tồn tại");
	         return "error";
	     }

	     // Lấy phí vận chuyển từ DeliveryService
	     if (deliveryId == null) {
	         model.addAttribute("error", "Vui lòng chọn phương thức giao hàng");
	         return "error";
	     }
	     Delivery delivery = deliServ.findById(deliveryId).orElse(null);
	     if (delivery == null) {
	         model.addAttribute("error", "Phương thức giao hàng không hợp lệ");
	         return "error";
	     }

	     // Tính tổng tiền từ giỏ hàng và cộng thêm phí vận chuyển
	     BigDecimal totalCost = cart.getTotalCost(); // Giả sử có phương thức để tính tổng tiền giỏ hàng
	     BigDecimal totalWithShipping = totalCost.add(delivery.getExtraCost()); // Tổng tiền cộng phí vận chuyển

	     // Tạo đối tượng đơn hàng mới
	     Order order = new Order();
	     order.setCart(cart);
	     order.setShipAddress(address);
	     order.setUser(user);
	     order.setStatus(OrderStatus.PENDING); // Đơn hàng đang chờ xử lý

	     // Tạo đối tượng thanh toán (COD)
	     Pays payment = new Pays();
	     payment.setPayMethod("COD"); // Phương thức thanh toán COD
	     payment.setTotal(totalWithShipping); // Lưu tổng tiền (bao gồm phí ship)

	     // Thiết lập thanh toán cho đơn hàng
	     order.setPayment(payment);

	     // Lưu đơn hàng và thanh toán vào cơ sở dữ liệu
	     orderServ.save(order);
	     paysServ.save(payment); // Lưu phương thức thanh toán

	     // Xóa tất cả sản phẩm trong giỏ hàng sau khi thanh toán
	     cartServ.deleteAllItem(user.getUserID());

	     // Chuyển hướng người dùng đến trang thành công
	     return "redirect:/user/packages";
	 }

	@PostMapping("/checkout-by-VNPay")
	public String payVN(HttpSession session, Model model, 
            @RequestParam("address") String address,
            @RequestParam(required = false) Integer deliveryId) {
		return null;
	}

}