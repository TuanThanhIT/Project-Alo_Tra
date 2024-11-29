package vn.iotstar.controllers.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.entity.User;
import vn.iotstar.services.IMilkTeaTypeService;
import vn.iotstar.services.IUserService;

@Controller
@RequestMapping("user")
public class UHomeController {
    
    @Autowired
    private IMilkTeaTypeService milkteaType;
    
    @Autowired
    private IUserService userService; // Giả sử bạn có một service để cập nhật thông tin người dùng
    
    @GetMapping("home")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            // Truyền thông tin fullname vào model
            model.addAttribute("user", user);
            
            // Đưa các loại trà sữa hiển thị lên sidebar
            List<MilkTeaType> list = milkteaType.findAll();
            model.addAttribute("listType", list);
            return "user/homeuser"; // Trả về trang homeuser.html
        }
        
        return "redirect:/login"; // Nếu không có session, chuyển hướng về trang login
    }
    
    // Hiển thị trang Hồ sơ
    @GetMapping("account")
    public String showAccountPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            model.addAttribute("user", user); // Truyền thông tin người dùng vào model
            return "user/account"; // Trả về trang account.html
        }
        
        return "redirect:/login"; // Nếu không có session, chuyển hướng về trang login
    }
    @PostMapping("/account/{id}")
    public String editAccount(Model model, @PathVariable("id") Integer userId, @ModelAttribute User updatedUser) {
        // Lấy user từ database
        Optional<User> optionalUser = userService.findById(userId);
        
        if (optionalUser.isPresent()) { // Kiểm tra nếu user tồn tại
            User user = optionalUser.get(); // Lấy đối tượng User từ Optional
            // Cập nhật thông tin
            user.setFullName(updatedUser.getFullName());
            user.setAddress(updatedUser.getAddress());
            user.setPhone(updatedUser.getPhone());
            userService.save(user); // Lưu user đã cập nhật vào database
            model.addAttribute("message", "Cập nhật thông tin thành công!");
        } else {
            model.addAttribute("message", "Không tìm thấy người dùng!");
        }
        
        model.addAttribute("user", optionalUser.orElse(null)); // Đưa user (nếu có) vào model
        return "user/account"; // Trả về trang account
    }

   
    
}
