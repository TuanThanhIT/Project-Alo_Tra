package vn.iotstar.controller.admin.MilkTeaType;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import vn.iotstar.entity.Discount;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.model.DiscountModel;
import vn.iotstar.model.MilkTeaTypeModel;
import vn.iotstar.service.admin.IDiscount;

@Controller
@RequestMapping("admin/discount")
public class DiscountController {
	@Autowired
	IDiscount discountService;

//	@RequestMapping("")
//	public String listCategories(ModelMap model) {
//		List<MilkTeaType> list = milkTeaTypeService.findAll();
//		System.out.println("Danh sách MilkTeaType: " + list); // Debug kiểm tra
//		model.addAttribute("milkTeaTypes", list); // Tên biến là "milkTeaTypes"
//		return "admin/milkTeaType/list"; // Tên View
//	}

	
	 @RequestMapping("")
	    public String listCategories(@RequestParam(defaultValue = "0") int page, ModelMap model) {
	        // Sử dụng pageable để giới hạn số sản phẩm hiển thị trên mỗi trang
	        Pageable pageable = PageRequest.of(page, 5); // 5 sản phẩm mỗi trang
	        Page<Discount> discountPage = discountService.findAll(pageable);

	        
	        // Thêm các thuộc tính vào model để truyền cho view
	        model.addAttribute("discounts", discountPage.getContent()); // Dữ liệu sản phẩm
	        model.addAttribute("currentPage", page); // Trang hiện tại
	        model.addAttribute("totalPages", discountPage.getTotalPages()); // Tổng số trang
	        return "admin/discount/list"; // View name
	    }
	@GetMapping("add")
	public String add(ModelMap model) {
		DiscountModel proModel = new DiscountModel();
		proModel.setIsEdit(false);
		// chuyển dữ liệu từ model vào biến product để đưa lên view
		model.addAttribute("discount", proModel);
		return "admin/discount/addOrEdit";
	}
	
	

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(@Valid @ModelAttribute("discount") DiscountModel discount,
	                                  BindingResult result, RedirectAttributes redirectAttributes) {
	    // Kiểm tra nếu có lỗi validate
	    if (result.hasErrors()) {
	        return new ModelAndView("admin/discount/addOrEdit"); // Nếu có lỗi, trở lại trang thêm hoặc sửa
	    }

	    // Kiểm tra milkTeaType không phải null
	    if (discount != null) {
	        Discount entity = new Discount(); 

	        // Copy từ Model (milkTeaType) sang Entity (entity)
	        BeanUtils.copyProperties(discount, entity);

	        // Lưu hoặc cập nhật vào cơ sở dữ liệu
	        discountService.save(entity);

	        // Thông báo kết quả
	        String message = "";
	        if (discount.getIsEdit()) {
	            message = "Discount đã được cập nhật thành công";
	        } else {
	            message = "Discount đã được thêm thành công";
	        }

	        // Thêm thông báo vào redirectAttributes (Flash Attribute)
	        redirectAttributes.addFlashAttribute("message", message);

	        // Chuyển hướng về danh sách Milk Tea Types
	        return new ModelAndView("redirect:/admin/discount");
	    }

	    // Nếu milkTeaType null, trả về lại form thêm hoặc sửa
	    return new ModelAndView("admin/discount/addOrEdit");
	}
	

	
	@GetMapping("delete/{discountID}")
	public ModelAndView delete(@PathVariable("discountID") Integer discountID, 
	                           RedirectAttributes redirectAttributes) {
	    // Xóa MilkTeaType theo ID
	    discountService.deleteById(discountID);
	    
	    // Thêm thông báo vào RedirectAttributes
	    redirectAttributes.addFlashAttribute("message", "Discount đã được xóa thành công");
	    
	    // Redirect về danh sách Milk Tea Types
	    return new ModelAndView("redirect:/admin/discount");
	}

	@GetMapping("edit/{discountID}")
	public ModelAndView edit(ModelMap model, @PathVariable("discountID") Integer discountID) {
		Optional<Discount> opt = discountService.findById(discountID);

		DiscountModel discountModel = new DiscountModel();
		if (opt.isPresent()) {
			Discount entity = opt.get();
			// Copy properties from entity to model
			BeanUtils.copyProperties(entity, discountModel);

			discountModel.setIsEdit(true);

			model.addAttribute("discount", discountModel);

			return new ModelAndView("admin/discount/addOrEdit", model);
		}

		model.addAttribute("message", "Discount không tồn tại");
		return new ModelAndView("forward:/admin/discount", model);
	}
//	@GetMapping("edit/{idType}")
//	public ModelAndView edit(ModelMap model, @PathVariable("idType") int idType) {
//		Optional<MilkTeaTypeEntity> opt = milkTeaTypeService.findById(idType);
//		MilkTeaTypeModel milkTea = new discountModel();
//		if (opt.isPresent()) {
//			MilkTeaTypeEntity entity = opt.get();
//			BeanUtils.copyProperties(entity, milkTea);
//			milkTea.setIsEdit(true);
//			model.addAttribute("milkTeaType", milkTea);
//			return new ModelAndView("admin/customize/customize-milk-tea-type", model);
//		}
//
//		model.addAttribute("message", "Type không tồn tại");
//		return new ModelAndView("forward:/admin/milk-tea", model);
//	}
}
