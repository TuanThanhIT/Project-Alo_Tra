package vn.iotstar.controller.admin.MilkTeaType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.entity.User;
import vn.iotstar.service.admin.IMilkTeaType;
import vn.iotstar.services.IUserService;

@RestController
@RequestMapping("admin/user")
public class UserController {
	@Autowired
	IUserService iUserService;
	
	@RequestMapping("")
	public String listCategories(ModelMap model) {
		List<User> list = iUserService.findAll();
		System.out.println("Danh sách users: " + list); // Debug kiểm tra
		model.addAttribute("users", list); // Tên biến là "milkTeaTypes"
		return "admin/user/list"; // Tên View
	}
	
	
//	@RequestMapping("toggleActive/{userID}")
//	@ResponseBody // Để trả về dữ liệu trực tiếp (thường cho AJAX)
//	public ResponseEntity<?> toggleUserActive(@PathVariable Integer userID) {
//	    try {
//	        Optional<User> userOpt = iUserService.findById(userID); // Tìm user
//	        if (userOpt.isPresent()) {
//	            User user = userOpt.get();
//	            user.setActive(!user.isActive()); // Đổi trạng thái
//	            iUserService.save(user); // Lưu vào DB
//	            return ResponseEntity.ok("Trạng thái tài khoản đã được thay đổi!");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	                    .body("Không tìm thấy người dùng có ID: " + userID);
//	        }
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("Có lỗi xảy ra: " + e.getMessage());
//	    }
//	}
	@RequestMapping("toggleActive/{userID}")
	public String toggleUserActive(@PathVariable Integer userID, ModelMap model) {
	    try {
	        Optional<User> userOpt = iUserService.findById(userID); // Tìm user
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            user.setActive(!user.isActive()); // Đổi trạng thái
	            iUserService.save(user); // Lưu vào DB

	            // Thêm thông báo vào ModelMap và quay lại trang danh sách
	            model.addAttribute("message", "Trạng thái tài khoản đã được thay đổi!");
	            return "forward:/admin/user"; // Quay lại trang danh sách users
	        } else {
	            model.addAttribute("message", "Không tìm thấy người dùng có ID: " + userID);
	            return "redirect:/admin/user"; // Quay lại trang danh sách nếu không tìm thấy người dùng
	        }
	    } catch (Exception e) {
	        model.addAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
	        return "redirect:/admin/user"; // Quay lại trang danh sách nếu có lỗi
	    }
	}


	
	
	
	
	
	
}
