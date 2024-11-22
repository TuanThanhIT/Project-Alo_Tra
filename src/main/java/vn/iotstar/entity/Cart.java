package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;

    @Column(name = "TotalCost")
    private BigDecimal totalCost;

    @Column(name = "TotalProduct")
    private int totalProduct;

    // Mối quan hệ với Order (1:N)
    @OneToMany(mappedBy = "cart")
    private List<Order> orders;

    // Mối quan hệ với MilkTea thông qua CartMilkTea (N:M)
    @ManyToMany
    @JoinTable(
        name = "CartMilkTea", 
        joinColumns = @JoinColumn(name = "CartID"), 
        inverseJoinColumns = @JoinColumn(name = "MilkTeaID")
    )
    private List<MilkTea> milkTeas;

    // Constructor không tham số
    public Cart() {
    }

    // Getters và Setters
    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<MilkTea> getMilkTeas() {
        return milkTeas;
    }

    public void setMilkTeas(List<MilkTea> milkTeas) {
        this.milkTeas = milkTeas;
    }
}
