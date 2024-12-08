package vn.iotstar.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CartMilkTea")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartMilkTea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key

    @ManyToOne
    @JoinColumn(name = "MilkTeaID", referencedColumnName = "milkTeaID")
    private MilkTea milkTea; // Foreign key to MilkTea

    @ManyToOne
    @JoinColumn(name = "CartID", referencedColumnName = "cartID")
    private Cart cart; // Foreign key to Cart

    @Column(name = "QuantityMilkTea")
    private int quantityMilkTea;

    // Quan hệ 1-1 với Sizes
    @OneToOne
    @JoinColumn(name = "SizeID", referencedColumnName = "sizeID")
    private Sizes size;
    
    // Tính tổng giá tiền (Không ánh xạ vào DB)
    @Transient
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
    	 BigDecimal basePrice = BigDecimal.ZERO;
    	    BigDecimal sizeExtraCost = BigDecimal.ZERO;

    	    // Giá cơ bản từ MilkTea
    	    if (milkTea != null && milkTea.getPrice() != null) {
    	        basePrice = milkTea.getPrice();
    	    }

    	    // Giá bổ sung từ Size
    	    if (size != null && size.getExtraCost() != null) {
    	        sizeExtraCost = size.getExtraCost();
    	    }

    	    // Tổng giá = (Giá MilkTea + Giá Size) * Số lượng
    	    return basePrice.add(sizeExtraCost).multiply(BigDecimal.valueOf(quantityMilkTea));
    }
}


