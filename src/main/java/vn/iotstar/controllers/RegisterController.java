package vn.iotstar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.iotstar.entity.Role;
import vn.iotstar.services.IRoleService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private IRoleService roleService;
	
	
	@GetMapping
	public String showRegisterPage(Model model)
	{
		List<Role> list = roleService.findByRoleNameNot("Admin");
		model.addAttribute("listRole", list);
		return "register";
	}
	
	
		
		

	
}