package vn.iotstar.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountModel {

    private int discountID;
    private BigDecimal percentDiscount;  // Use BigDecimal for precision
    private LocalTime applyTime;  // Use LocalTime for time without date
    private LocalTime endTime;  // Use LocalTime for time without date
    private LocalDate applyDay;  // Use LocalDate for date
	private Boolean isEdit = false;

}
