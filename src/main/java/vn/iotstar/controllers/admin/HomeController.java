package vn.iotstar.controllers.admin;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.IBranchService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.IIncomeService;
import vn.iotstar.services.IUserService;

@Controller
@RequestMapping("admin")
public class HomeController {
	@Autowired
	IUserService iUserService;

	@Autowired
	IIncomeService iIncomeService;

	@Autowired
	IBranchService branchService;

	@Autowired
	ICartService cartService;

	@RequestMapping("")
	public String listCategories(ModelMap model, HttpServletRequest request) {
		// Lấy thông tin user từ session trực tiếp
		HttpSession session = request.getSession(false); // Lấy session nếu có
		User user = (User) session.getAttribute("account"); // Lấy thông tin user từ session
		// Kiểm tra xem user có tồn tại và role của user có phải là admin (roleID == 1)
		if (user != null && user.getRoleID() == 1) {
			long countUser = iUserService.countByRole("User");
			model.addAttribute("countUser", countUser); 

			double totalIncome = iIncomeService.sumIncomeValue();

			// Định dạng số với đơn vị VNĐ
			NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
			String formattedIncome = format.format(totalIncome) + " VNĐ";
			model.addAttribute("income", formattedIncome); // Thêm vào model

			long totalBranch = branchService.count();
			model.addAttribute("totalBranch", totalBranch); // Thêm vào model

			long totalCart = cartService.getTotalProductCount();
			model.addAttribute("totalCart", totalCart); // Thêm vào model
			
			//////////// session chart//////////////
			//pie chart
			long countSeller = iUserService.countByRole("Seller");
			model.addAttribute("countSeller", countSeller); 
			long countShipper = iUserService.countByRole("Shipper");
			model.addAttribute("countShipper", countShipper); 
			
			// line graph
			 int[] months = new int[12];
			    double[] totalIncomePerMonth = new double[12];

			    // Khởi tạo tháng và doanh thu mặc định là 0
			    for (int i = 0; i < 12; i++) {
			        months[i] = i + 1;
			        totalIncomePerMonth[i] = 0;
			    }

			    // Giả sử đây là dữ liệu lấy từ cơ sở dữ liệu
			    List<Object[]> monthlyIncome = iIncomeService.findMonthlyIncome(2024);

			    // Gán doanh thu vào các tháng tương ứng
			    for (Object[] row : monthlyIncome) {
			        int month = (int) row[0];  // Lấy tháng
			        double income = (double) row[1];  // Lấy doanh thu
			        totalIncomePerMonth[month - 1] = income;  // Gán doanh thu vào tháng tương ứng
			    }

			    // Chuyển mảng thành chuỗi JSON để sử dụng trong view
			    ObjectMapper objectMapper = new ObjectMapper();
			    try {
			        String monthsJson = objectMapper.writeValueAsString(months);
			        String totalIncomeJson = objectMapper.writeValueAsString(totalIncomePerMonth);

			        // Thêm dữ liệu vào model
			        model.addAttribute("monthsJson", monthsJson);
			        model.addAttribute("totalIncomeJson", totalIncomeJson);
			    } catch (JsonProcessingException e) {
			        e.printStackTrace();
			    }
			
			    ///bar chart
			 // Lấy dữ liệu từ cơ sở dữ liệu
			    List<Object[]> branchIncome = iIncomeService.findTotalIncomeByBranch();

			    // Lưu trữ dữ liệu doanh thu của từng chi nhánh
			    List<Map<String, Object>> branchIncomeData = new ArrayList<>();
			    if (branchIncome != null && !branchIncome.isEmpty()) { // Kiểm tra nếu dữ liệu không null hoặc rỗng
			        for (Object[] row : branchIncome) {
			            try {
			                int branchId = (int) row[0];  // ID chi nhánh
			                double income = (double) row[1];  // Doanh thu chi nhánh

			                // Tạo đối tượng lưu trữ dữ liệu doanh thu
			                Map<String, Object> data = new HashMap<>();
			                data.put("branchId", branchId);
			                data.put("income", income);
			                branchIncomeData.add(data);
			            } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
			                ex.printStackTrace(); // Log lỗi nếu dữ liệu không đúng định dạng
			            }
			        }
			    }

			    // Chuyển mảng dữ liệu thành chuỗi JSON để sử dụng trong view
			    ObjectMapper objectMapper2 = new ObjectMapper();
			    try {
			        String branchIncomeJson = objectMapper2.writeValueAsString(branchIncomeData);
			        model.addAttribute("branchIncomeJson", branchIncomeJson);
			    } catch (JsonProcessingException e) {
			        e.printStackTrace(); // Log lỗi JSON
			    }

			return "admin/index"; // Tên View
		} else {
			return "user/error"; // Nếu không phải admin hoặc không có user thì trả về trang lỗi
		}
	}
}
