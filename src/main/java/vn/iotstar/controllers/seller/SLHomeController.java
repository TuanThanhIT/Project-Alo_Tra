package vn.iotstar.controllers.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("seller")
public class SLHomeController {
	@GetMapping("/")
	public String revenue() {
		return "seller/revenue/revenue";
	}
	
}
