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

import vn.iotstar.services.ICartMilkTeaService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.IMilkTeaService;
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
	public String addToCart(@RequestParam int id, 
	                        @RequestParam int quantity, 
	                        @RequestParam String size,
	                        HttpSession session, 
	                        Model model) {
	    // Lấy thông tin người dùng từ session
	    User user = (User) session.getAttribute("account");
	    if (user == null) {
	        // Nếu user chưa đăng nhập, chuyển hướng về trang đăng nhập
	        return "redirect:/login";
	    }

	    // Tìm giỏ hàng theo User ID
	    Cart cart = cartServ.findByUserId(user.getUserID()).orElse(null);
	    if (cart == null) {
	        // Nếu giỏ hàng chưa tồn tại, tạo giỏ hàng mới
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setMilkTeas(new ArrayList<>());
	        cartServ.save(cart);
	    }

	    // Tìm sản phẩm MilkTea
	    MilkTea milkTea = milkTeaServ.findById(id)
	            .orElseThrow(() -> new RuntimeException("MilkTea không tồn tại"));

	    // Tìm kích thước Size
	    Sizes milkSize = sizeServ.findByName(size);
	    if (milkSize == null) {
	        throw new RuntimeException("Kích thước không hợp lệ");
	    }

	    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
	    Optional<CartMilkTea> existingCartMilkTea = cart.getMilkTeas().stream()
	            .filter(cmt -> cmt.getMilkTea().getMilkTeaID() == id && cmt.getSize().equals(milkSize))
	            .findFirst();

	    if (existingCartMilkTea.isPresent()) {
	        // Nếu sản phẩm đã tồn tại, tăng số lượng
	        CartMilkTea cartMilkTea = existingCartMilkTea.get();
	        cartMilkTea.setQuantityMilkTea(cartMilkTea.getQuantityMilkTea() + quantity);
	    } else {
	        // Nếu sản phẩm chưa tồn tại, thêm sản phẩm mới vào giỏ hàng
	        CartMilkTea cartMilkTea = new CartMilkTea();
	        cartMilkTea.setMilkTea(milkTea);
	        cartMilkTea.setCart(cart);
	        cartMilkTea.setQuantityMilkTea(quantity);
	        cartMilkTea.setSize(milkSize);
	        cart.getMilkTeas().add(cartMilkTea);
	    }

	    // Lưu lại giỏ hàng
	    cartServ.save(cart);

	    // Chuyển hướng về trang chủ
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
	public String removeItem(@RequestParam int id, HttpSession session) {
	    // Lấy thông tin người dùng từ session
	    User user = (User) session.getAttribute("account");
	    if (user == null) {
	        // Nếu user chưa đăng nhập, chuyển hướng về trang đăng nhập
	        return "redirect:/login";
	    }

	    // Tìm giỏ hàng theo User ID
	    Cart cart = cartServ.findByUserId(user.getUserID()).orElse(null);
	    if (cart == null) {
	        throw new RuntimeException("Giỏ hàng không tồn tại");
	    }

	    // Tìm sản phẩm cần xóa (CartMilkTea) theo ID
	    CartMilkTea cartMilkTea = cmilkTeaServ.findById(id)
	            .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại trong giỏ hàng"));

	    // Xóa sản phẩm khỏi danh sách trong giỏ hàng
	    cart.getMilkTeas().removeIf(cmt -> cmt.getId() == id);

	    // Xóa sản phẩm khỏi cơ sở dữ liệu
	    cmilkTeaServ.deleteById(id);

	    // Lưu lại giỏ hàng sau khi cập nhật
	    cartServ.save(cart);

	    // Chuyển hướng về trang giỏ hàng
	    return "redirect:/cart";
	}

}
