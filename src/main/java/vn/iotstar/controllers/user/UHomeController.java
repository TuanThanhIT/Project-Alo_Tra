package vn.iotstar.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;


@Controller
@RequestMapping("user")
public class UHomeController {
	@GetMapping("home")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            // Truyền thông tin fullname vào model
            model.addAttribute("user", user);
            return "user/homeuser"; // Trả về trang homeuser.html
        }
        
        return "redirect:/login"; // Nếu không có session, chuyển hướng về trang login
    }
}
