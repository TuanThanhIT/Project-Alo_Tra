package vn.iotstar.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

@Component
public class SessionInterceptor implements HandlerInterceptor{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            // Đưa thông tin user vào request scope để Thymeleaf có thể sử dụng
            User user = (User) session.getAttribute("account");
            request.setAttribute("user", user);
        }
        return true; // Tiếp tục request
    }

}
