package vn.iotstar.controllers.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iotstar.entity.MilkTea;
import vn.iotstar.service.seller.IMilkTeaService;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("seller")
public class SLHomeController {
	@Autowired
	private IMilkTeaService iMilkTeaService;
	
	@GetMapping("/revenue")
	public String revenue() {
		return "seller/revenue/revenue";
	}
	
	@GetMapping("/branch")
	public String branch() {
		return "seller/branch/branchhome";
	}
	
	@GetMapping("/milkTeas")
	public String listMilkTea(Model model) {
		List<MilkTea> milkTeas = iMilkTeaService.findAll();
		model.addAttribute("milkTeas", milkTeas);
		return "seller/MilkTea/list-MilkTea";
	}
}
