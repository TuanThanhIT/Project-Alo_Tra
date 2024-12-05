package vn.iotstar.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.models.UserDto;
import vn.iotstar.services.IRoleService;
import vn.iotstar.services.IUserService;
import vn.iotstar.utils.PathConstants;


@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	// Hiển thị trang đăng ký
	@GetMapping
	public String showRegisterPage(Model model) {
		// Lấy danh sách các role không phải "Admin"
		List<Role> list = roleService.findByRoleNameNot("Admin");
		model.addAttribute("listRole", list);
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register";
	}

	@PostMapping
	public String createUser(@Valid @ModelAttribute UserDto userDto, Model model)
	{
		String message = "";
		
		String uploadDirectory = PathConstants.UPLOAD_DIRECTORY;
		
	    Date createdAt = new Date();
	    MultipartFile image = userDto.getImage();
	    String originalFileName = image.getOriginalFilename();
	    String storageFileName = createdAt.getTime() + "_" + originalFileName;

	    try {
	        // Kiểm tra định dạng file (chỉ cho phép jpg, png, jpeg)
	        if (!originalFileName.toLowerCase().endsWith(".jpg") &&
	            !originalFileName.toLowerCase().endsWith(".png") &&
	            !originalFileName.toLowerCase().endsWith(".jpeg")) {
	            message = "Đăng kí không thành công, chỉ chấp nhận các định dạng ảnh: JPG, PNG, JPEG.";
	            model.addAttribute("alert", message);
	            return "login";
	        }
	        // Thư mục lưu trữ ảnh
	        Path uploadPath = Paths.get(uploadDirectory);  // Chỉ đường dẫn đến thư mục uploads

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);  // Nếu thư mục không tồn tại, tạo thư mục
	        }

	        try (InputStream inputStream = image.getInputStream()) {
	            // Đảm bảo lưu vào đúng thư mục uploads với tên file lưu trữ
	            Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
	        }
	    } catch (Exception ex) {
	    	System.out.println("Exception:" + ex.getMessage());
	    }
		
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setEmail(userDto.getEmail());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setDate(createdAt);
		user.setActive(true);
		user.setImage(storageFileName);
		user.setRoleID(userDto.getRoleID());
		
		
		if(userService.existsByUserName(user.getUserName()))
		{
			message = "Tên đăng nhập đã tồn tại. Đăng kí không thành công";
			model.addAttribute("alert", message);
			return "login";
		}
		else
		{
			message = "Đăng kí thành công. Vui lòng đăng nhập ở đây";
			model.addAttribute("alert", message);
			userService.addUser(user);
			return "login";
		}
	}

	
}
	    