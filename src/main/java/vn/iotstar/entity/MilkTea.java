package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "MilkTea")
public class MilkTea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int milkTeaID;

    @ManyToOne
    @JoinColumn(name = "TypeMilkTeaID", referencedColumnName = "milkTeaTypeID")
    private MilkTeaType milkTeaType;
    
    // Mối quan hệ N:M với Ingredient thông qua MilkTea_Ingredient
    @ManyToMany
    @JoinTable(
        name = "MilkTea_Ingredient",
        joinColumns = @JoinColumn(name = "MilkTeaID"),
        inverseJoinColumns = @JoinColumn(name = "IngredientID")
    )
    private List<Ingredient> ingredients;

    @ManyToMany(mappedBy = "milkTeas")  // mappedBy tương ứng với trường trong Branch
    private List<Branch> branches;  // Mối quan hệ N:M với Branch

    @Column(name = "MilkTeaName", columnDefinition = "nvarchar(255)")
    private String milkTeaName;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "DiscountPrice")
    private BigDecimal discountPrice;

    @Column(name = "Description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(columnDefinition = "nvarchar(max)")
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "DiscountID", referencedColumnName = "discountID")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "SizeID", referencedColumnName = "sizeID")
    private Sizes size;

    @ManyToOne
    @JoinColumn(name = "RateID", referencedColumnName = "rateID")
    private Rate rate;

    @Column(name = "QuantityStock")
    private int quantityStock;

    // Các getter và setter khác

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
