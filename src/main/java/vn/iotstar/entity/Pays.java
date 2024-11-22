package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payID;

    @Column(name = "PayName")
    private String payName;

    // Mối quan hệ 1:N với Order
    @OneToMany(mappedBy = "pays") // mappedBy phải trỏ đến thuộc tính "pays" trong lớp Order
    private List<Order> orders;

    // Constructor không tham số
    public Pays() {
    }

    // Getters và Setters
    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
