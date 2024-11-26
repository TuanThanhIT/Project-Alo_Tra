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
	        HttpSession session = request.getSession(false);

	        if (session != null && session.getAttribute("account") != null) {
	            User user = (User) session.getAttribute("account");

	            if (user.getRoleID() == 1) {
	                return "redirect:/admin/home";
	            } 
	            else if(user.getRoleID() == 2) {
	                return "redirect:/seller/home";
	            }
	            else if(user.getRoleID() == 3)
	            {
	            	return "redirect:/user/home";
	            }
	        }

	        return "redirect:/login";
	    }
}
