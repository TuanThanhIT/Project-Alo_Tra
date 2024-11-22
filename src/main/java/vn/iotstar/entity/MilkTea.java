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

    @Column(name = "MilkTeaName")
    private String milkTeaName;

    @Column(name = "Image")
    private String image;

    @Column(name = "Price")
    private BigDecimal price;  // Using BigDecimal for precision (decimal in SQL)

    @Column(name = "DiscountPrice")
    private BigDecimal discountPrice;  // Using BigDecimal for precision (decimal in SQL)

    @Column(name = "Description")
    private String description;

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

    // Constructor không tham số
    public MilkTea() {
    }

    // Getters và Setters
    public int getMilkTeaID() {
        return milkTeaID;
    }

    public void setMilkTeaID(int milkTeaID) {
        this.milkTeaID = milkTeaID;
    }

    public MilkTeaType getMilkTeaType() {
        return milkTeaType;
    }

    public void setMilkTeaType(MilkTeaType milkTeaType) {
        this.milkTeaType = milkTeaType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getMilkTeaName() {
        return milkTeaName;
    }

    public void setMilkTeaName(String milkTeaName) {
        this.milkTeaName = milkTeaName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }
}
