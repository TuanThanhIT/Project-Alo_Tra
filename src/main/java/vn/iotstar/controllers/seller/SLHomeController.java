package vn.iotstar.controllers.seller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iotstar.entity.Branch;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.service.seller.IBranchService;
import vn.iotstar.service.seller.IMilkTeaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("seller")
public class SLHomeController {
	@Autowired
	private IMilkTeaService iMilkTeaService;
	
	@Autowired
	private IBranchService iBranService;

	private Branch branch;


	
	
	@GetMapping("/revenue")
	public String revenue() {
		return "seller/revenue/revenue";
	}
	
	@GetMapping("/branch")
	public String branch(Model model) {
		Optional<Branch> branchOptional = iBranService.findById(1);
	    if (branchOptional.isPresent()) {
	        model.addAttribute("branch", branchOptional.get());
	    } else {
	        model.addAttribute("branch", new Branch());
	    }
	    return "seller/branch/branchhome";
	}
	
	@GetMapping("/milkTeas")
	public String listMilkTea(Model model) {
		List<MilkTea> milkTeas = iMilkTeaService.findAll();
		model.addAttribute("milkTeas", milkTeas);
		return "seller/MilkTea/list-MilkTea";
	}
	
	@GetMapping("/add-milkTeas")
	public String addMilkTea(Model model) {
		MilkTea milkTea = new MilkTea();
		model.addAttribute("milkTea", milkTea);
		return "seller/MilkTea/add-MilkTea";
	}
	
	@GetMapping("/add-branch")
	public String addBranch(Model model) {
		Branch branch = new Branch();
		model.addAttribute("branch", branch);
		return "seller/Branch/add-Branch";
	}
	
	@PostMapping("/saveBranch")
	public String saveBranch(@ModelAttribute("branch") Branch branch) {
		this.branch = branch;
		iBranService.save(branch);
		return "redirect:/seller/branch";
	}
}
