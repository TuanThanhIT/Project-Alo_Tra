package vn.iotstar.entity;

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
}