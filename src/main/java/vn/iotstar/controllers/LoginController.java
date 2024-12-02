package vn.iotstar.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public String showLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Kiểm tra xem người dùng đã đăng nhập chưa qua session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            return "redirect:/waiting";  // Nếu đã đăng nhập thì chuyển hướng tới trang đích
        }

        // Kiểm tra cookie nhớ mật khẩu
        Cookie[] cookies = request.getCookies();
        String username = "";
        String password = "";  // Mật khẩu sẽ được thêm vào nếu có cookie nhớ mật khẩu
        boolean rememberMe = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("COOKIE_REMEMBER".equals(cookie.getName())) {
                    String[] userData = cookie.getValue().split("::");
                    if (userData.length == 2) {
                        username = userData[0];  // Lấy tên đăng nhập từ cookie
                        password = userData[1];  // Lấy mật khẩu từ cookie
                        rememberMe = true;  // Đánh dấu là đã chọn nhớ mật khẩu
                    }
                }
            }
        }

        // Truyền vào model để hiển thị thông tin trên form
        model.addAttribute("username", username);
        model.addAttribute("password", password);  // Truyền mật khẩu vào model
        model.addAttribute("rememberMe", rememberMe);  // Checkbox nhớ mật khẩu được đánh dấu nếu cookie tồn tại

        return "login";
    }


    @PostMapping
    public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam(value = "remember", required = false) String remember, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        boolean isRememberMe = "on".equals(remember);  // Kiểm tra xem người dùng có chọn nhớ mật khẩu không
        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            model.addAttribute("alert", alertMsg);
            return "login";
        }

        // Kiểm tra thông tin đăng nhập
        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            // Nếu chọn nhớ mật khẩu thì lưu cookie
            if (isRememberMe) {
                saveRememberMe(response, username, password);
            } else {
                deleteRememberMeCookie(response);  // Nếu không chọn thì xóa cookie
            }

            return "redirect:/waiting";
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            model.addAttribute("alert", alertMsg);
            return "login";
        }
    }

    // Lưu cookie nhớ mật khẩu
    private void saveRememberMe(HttpServletResponse response, String username, String password) {
        String userData = username + "::" + password;
        Cookie cookie = new Cookie("COOKIE_REMEMBER", userData);
        cookie.setMaxAge(30 * 60);  // Cookie tồn tại trong 30 phút
        cookie.setPath("/");        // Áp dụng cho toàn bộ các URL
        response.addCookie(cookie);
    }

    // Xóa cookie khi không chọn "Nhớ mật khẩu"
    private void deleteRememberMeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("COOKIE_REMEMBER", "");
        cookie.setMaxAge(0);  // Xóa cookie ngay lập tức
        cookie.setPath("/");  // Áp dụng cho toàn bộ các URL
        response.addCookie(cookie);
    }
}


