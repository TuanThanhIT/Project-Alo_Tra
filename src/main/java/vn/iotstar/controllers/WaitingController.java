package vn.iotstar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

@Controller
@RequestMapping("/waiting")
public class WaitingController {
    
    @GetMapping
    public String handleWaiting(HttpServletRequest request, HttpServletResponse response) {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false);

        // Kiểm tra xem session có tồn tại và "account" có phải là một User hay không
        if (session != null && session.getAttribute("account") instanceof User) {
            User user = (User) session.getAttribute("account");

            // Kiểm tra vai trò của người dùng và chuyển hướng tới trang phù hợp
            if (user.getRoleID() == 1) {
                return "redirect:/user/home";  // Nếu là admin
            } 
            else if (user.getRoleID() == 2) {
                return "redirect:/user/home";  // Nếu là người bán
            }
            else if (user.getRoleID() == 3) {
                return "redirect:/user/home";  // Nếu là người dùng
            }
        }

        // Nếu không có tài khoản người dùng trong session, chuyển hướng về trang đăng nhập
        return "redirect:/login";
    }
}
