package vn.iotstar.controllers.seller;

import java.beans.PropertyEditorSupport;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.Branch;
import vn.iotstar.entity.BranchMilkTea;
import vn.iotstar.entity.MilkTea;
import vn.iotstar.entity.MilkTeaType;
import vn.iotstar.model.BranchDto;
import vn.iotstar.model.MilkTeaDto;
import vn.iotstar.service.seller.IBranchMilkTeaService;
import vn.iotstar.service.seller.IBranchService;
import vn.iotstar.service.seller.IMilkTeaService;
import vn.iotstar.service.seller.IMilkTeaTypeService;
import vn.iotstar.utils.PathConstants;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("seller")
public class SLHomeController {
	@Autowired
	private IMilkTeaService iMilkTeaService;
	
	@Autowired
	private IBranchService iBranhService;
	
	@Autowired
	private IBranchMilkTeaService iBranchMilkTeaService;
	
	@Autowired
	private IMilkTeaTypeService iMilkTeaTypeService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MilkTeaType.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    int id = Integer.parseInt(text);
                    Optional<MilkTeaType> milkTeaType = iMilkTeaTypeService.findById(id);
                    setValue(milkTeaType.orElse(null));
                }
            }
        });
    }
	
	@GetMapping("/revenue")
	public String revenue() {
		return "seller/revenue/revenue";
	}
	
	@GetMapping("/branch")
	public String branch(Model model) {
		Optional<Branch> branchOptional = iBranhService.findById(1);
	    if (branchOptional.isPresent()) {
	        model.addAttribute("branch", branchOptional.get());
	    } else {
	        model.addAttribute("branch", new Branch());
	    }
	    return "seller/branch/branchhome";
	}
	
	@GetMapping("/milkTeas")
	public String listMilkTea(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(required = false) Integer typeMilkTeaID) {
	    List<MilkTeaType> listType = iMilkTeaTypeService.findAll();
		Page<MilkTea> milkTeas;
	    if (typeMilkTeaID == null) {
	        milkTeas = iMilkTeaService.findAll(pageNo);
	    } else {
	        milkTeas = iMilkTeaService.findByMilkTeaType_MilkTeaTypeID(typeMilkTeaID, pageNo);
	    }

	    model.addAttribute("totalPage", milkTeas.getTotalPages());
	    model.addAttribute("listType", listType);
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("milkTeas", milkTeas);
	    return "seller/milkTea/list-MilkTea";
	}

	
	
	@GetMapping("/add-branch")
	public String addBranch(Model model) {
		Branch branch = new Branch();
		model.addAttribute("branch", branch);
		return "seller/Branch/add-Branch";
	}
	
	@PostMapping("/saveBranch")
	public String saveBranch(@ModelAttribute BranchDto branchDto, Model model) {
		String uploadDirectory = PathConstants.UPLOAD_DIRECTORY;
		List<String> storedFileNames = new ArrayList<>();
		
		try {
	        for (MultipartFile image : branchDto.getImages()) {
	            if (!image.isEmpty()) {
	                String originalFileName = image.getOriginalFilename();
	                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

	                if (!fileExtension.equals(".jpg") && !fileExtension.equals(".png") && !fileExtension.equals(".jpeg")) {
	                    model.addAttribute("alert", "Chỉ chấp nhận ảnh JPG, PNG, JPEG");
	                    return "seller/branch/add-Branch";
	                }

	                String storageFileName = System.currentTimeMillis() + "_" + originalFileName;

	                Path uploadPath = Paths.get(uploadDirectory);
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath);
	                }

	                try (InputStream inputStream = image.getInputStream()) {
	                    Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
	                }
	                storedFileNames.add(storageFileName);
	            }
	        }
	    } catch (Exception ex) {
	        model.addAttribute("alert", "Có lỗi xảy ra khi upload ảnh: " + ex.getMessage());
	        return "seller/branch/add-Branch";
	    }
		
		Branch branch = new Branch();
		branch.setAddress(branchDto.getAddress());
		branch.setOpenTime(branchDto.getOpenTime());
		branch.setCloseTime(branch.getCloseTime());
		branch.setIntroduction(branchDto.getIntroduction());
		branch.setDescription(branchDto.getDescription());
		
		branch.setImages(String.join(",", storedFileNames));

	    iBranhService.save(branch); // Gọi service lưu vào DB
	    model.addAttribute("success", "Thêm thông tin thành công!");
		
		
		return "redirect:/seller/branch";
	}
	
	@GetMapping("/add-milkTea")
	public String showAddMilkTeaForm(Model model) {
		List<MilkTeaType> list = iMilkTeaTypeService.findAll();
		model.addAttribute("listType", list);
	    model.addAttribute("milkTea", new MilkTea());
	    return "seller/milkTea/add-MilkTea";
	}

	@PostMapping("/milktea/save")
	public String saveMilkTea(@ModelAttribute MilkTeaDto milkTeaDto, Model model) {
	    String uploadDirectory = PathConstants.UPLOAD_DIRECTORY; // Thư mục lưu ảnh
	    List<String> storedFileNames = new ArrayList<>(); // Danh sách tên ảnh lưu trong DB
	    
	    try {
	        // Duyệt qua từng file trong mảng MultipartFile[]
	        for (MultipartFile image : milkTeaDto.getImages()) {
	            if (!image.isEmpty()) {
	                String originalFileName = image.getOriginalFilename();
	                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
	                
	                // Kiểm tra định dạng ảnh hợp lệ
	                if (!fileExtension.equals(".jpg") && !fileExtension.equals(".png") && !fileExtension.equals(".jpeg")) {
	                    model.addAttribute("alert", "Chỉ chấp nhận ảnh JPG, PNG, JPEG");
	                    return "seller/milkTea/add-MilkTea";
	                }

	                // Tạo tên file lưu trữ
	                String storageFileName = System.currentTimeMillis() + "_" + originalFileName;

	                // Lưu file vào thư mục
	                Path uploadPath = Paths.get(uploadDirectory);
	                if (!Files.exists(uploadPath)) {
	                    Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa tồn tại
	                }

	                try (InputStream inputStream = image.getInputStream()) {
	                    Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
	                }

	                // Thêm tên file vào danh sách
	                storedFileNames.add(storageFileName);
	            }
	        }
	    } catch (Exception ex) {
	        model.addAttribute("alert", "Có lỗi xảy ra khi upload ảnh: " + ex.getMessage());
	        return "seller/milkTea/add-MilkTea";
	    }

	    // Lưu thông tin MilkTea vào cơ sở dữ liệu
	    MilkTea milkTea = new MilkTea();
	    milkTea.setMilkTeaName(milkTeaDto.getMilkTeaName());
	    milkTea.setPrice(milkTeaDto.getPrice());
	    milkTea.setDiscountPrice(milkTeaDto.getDiscountPrice());
	    milkTea.setDescription(milkTeaDto.getDescription());
	    milkTea.setIntroduction(milkTeaDto.getIntroduction());
	    milkTea.setMilkTeaType(milkTeaDto.getMilkTeaType()); // Gán trực tiếp đối tượng MilkTeaType

	    // Nối danh sách tên file ảnh thành chuỗi, cách nhau bởi dấu phẩy
	    milkTea.setImage(String.join(",", storedFileNames));

	    iMilkTeaService.save(milkTea); // Gọi service lưu vào DB
	    model.addAttribute("success", "Thêm trà sữa thành công!");
	    
	    Optional<Branch> branch = iBranhService.findById(1);
	    Branch enBranch = branch.get();
	    BranchMilkTea branchMilkTea = new BranchMilkTea(enBranch, milkTea);
	    iBranchMilkTeaService.save(branchMilkTea);
	    return "redirect:/seller/milkTeas"; // Chuyển hướng đến danh sách MilkTea
	}
}
