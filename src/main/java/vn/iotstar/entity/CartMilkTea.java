package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CartMilkTea")
public class CartMilkTea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary key added

    @ManyToOne
    @JoinColumn(name = "MilkTeaID", referencedColumnName = "milkTeaID")
    private MilkTea milkTea;  // Foreign key to MilkTea

    @ManyToOne
    @JoinColumn(name = "CartID", referencedColumnName = "cartID")
    private Cart cart;  // Foreign key to Cart

    @Column(name = "QuantityMilkTea")
    private int quantityMilkTea;  // Quantity of milk tea

    // Constructor không tham số
    public CartMilkTea() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MilkTea getMilkTea() {
        return milkTea;
    }

    public void setMilkTea(MilkTea milkTea) {
        this.milkTea = milkTea;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantityMilkTea() {
        return quantityMilkTea;
    }

    public void setQuantityMilkTea(int quantityMilkTea) {
        this.quantityMilkTea = quantityMilkTea;
    }
}
