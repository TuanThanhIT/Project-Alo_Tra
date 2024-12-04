package vn.iotstar.controllers.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartMilkTea;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.entity.Sizes;
import vn.iotstar.entity.User;
import vn.iotstar.service.seller.IMilkTeaService;
import vn.iotstar.service.seller.IMilkTeaTypeService;
import vn.iotstar.services.ICartMilkTeaService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.ISizeService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private IMilkTeaService milkTeaServ;
	@Autowired
	private ICartService cartServ;
	@Autowired
	private ICartMilkTeaService cmilkTeaServ;

	@Autowired
	private ISizeService sizeServ;
	@GetMapping("/addToCart")
	public String addToCart(@RequestParam int id, @RequestParam int quantity, @RequestParam String size,
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("account");
		if (user == null) {
			return "redirect:/login";
		}
		Cart cart = cartServ.findByUserId(user.getUserID()).get();
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cart.setMilkTeas(new ArrayList<>());
			cartServ.save(cart);
		}
		MilkTea milkTea = milkTeaServ.findById(id).orElseThrow(() -> new RuntimeException("MilkTea không tồn tại"));
		CartMilkTea cartMilkTea = new CartMilkTea();
		cartMilkTea.setMilkTea(milkTea);
		cartMilkTea.setCart(cart);
		cartMilkTea.setQuantityMilkTea(quantity);
		Sizes Milksize = sizeServ.findByName(size);
		cartMilkTea.setSize(Milksize);
		cart.getMilkTeas().add(cartMilkTea);
		cartServ.save(cart);
		return "redirect:/home";
	}

	@GetMapping({ "", "/" })
	public String cartGet(HttpSession session, Model model) {
	    // Lấy thông tin User từ session
	    User user = (User) session.getAttribute("account");

	    if (user == null) {
	        // Nếu không có user trong session, chuyển hướng đến trang đăng nhập hoặc xử lý phù hợp
	        return "redirect:/login";
	    }

	    // Tìm kiếm Cart bằng User ID
	    Optional<Cart> cartOptional = cartServ.findByUserId(user.getUserID());
	    Cart cart;

	    if (cartOptional.isPresent()) {
	        // Nếu Cart tồn tại
	        cart = cartOptional.get();
	    } else {
	        // Nếu không tìm thấy Cart, tạo mới Cart
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setMilkTeas(new ArrayList<>());
	        cartServ.save(cart); // Lưu Cart mới vào cơ sở dữ liệu
	    }

	    // Thêm danh sách MilkTeas vào model
	    model.addAttribute("listCart", cart.getMilkTeas());

	    // Tính tổng giá tiền và thêm vào model
	    BigDecimal totalPrice = cmilkTeaServ.calculateTotalPrice(cart.getCartID());
	    model.addAttribute("total", totalPrice);

	    // Trả về trang "user/cart"
	    return "user/cart";
	}

	
	@GetMapping("/remove")
	public String removeItem(@RequestParam int id,HttpSession session) {
		User user = (User) session.getAttribute("account");
		Cart cart = cartServ.findByUserId(user.getUserID()).get();
		CartMilkTea cMilkTea = cmilkTeaServ.findById(id).get();
		cart.getMilkTeas().remove(cMilkTea);
		cmilkTeaServ.deleteById(cMilkTea);
		return "user/cart";
	}
}
