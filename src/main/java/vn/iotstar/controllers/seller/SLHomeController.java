package vn.iotstar.controllers.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("seller")
public class SLHomeController {
	@GetMapping("/revenue")
	public String revenue() {
		return "seller/revenue/revenue";
	}
	
	@GetMapping("/branch")
	public String branch() {
		return "seller/branch/branchhome";
	}
	
}
