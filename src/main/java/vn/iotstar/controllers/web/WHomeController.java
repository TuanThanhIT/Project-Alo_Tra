package vn.iotstar.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.iotstar.entity.Branch;
import vn.iotstar.services.IIncomeService;


@Controller
public class WHomeController {
	
	@Autowired
	private IIncomeService iIncomeService;
	
	@GetMapping({"/", "/web/home"})
	public String index(Model model)
	{
		List<Branch> list = iIncomeService.getTop4BranchesByTotalValue();
		model.addAttribute("listBranch", list);
		return "web/home";
	}

}