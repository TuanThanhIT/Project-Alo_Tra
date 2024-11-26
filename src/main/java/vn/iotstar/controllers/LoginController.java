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
import vn.iotstar.utils.Constant;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IUserService userService;

	@GetMapping
	public String showLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		// Check session
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			return "redirect:/waiting";
		}

		// Check cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constant.COOKIE_REMEMBER)) {
					session = request.getSession(true);
					session.setAttribute("account", cookie.getValue());
					return "redirect:/waiting";
				}
			}
		}

		return "login";
	}

	@PostMapping
	public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "remember", required = false) String remember, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		boolean isRememberMe = "on".equals(remember);
		String alertMsg = "";
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			model.addAttribute("alert", alertMsg);
			return "login";
		}
		User user = userService.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRememberMe(response, username);
			}

			return "redirect:/waiting";
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			model.addAttribute("alert", alertMsg);
			return "login";
		}
	}

	private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }
}
