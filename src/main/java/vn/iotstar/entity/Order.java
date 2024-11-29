package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CartID", referencedColumnName = "cartID")
    private Cart cart;  // Một đơn hàng thuộc về một giỏ hàng

    @ManyToOne
    @JoinColumn(name = "BranchID", referencedColumnName = "branchID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "StatusID", referencedColumnName = "statusID")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ShipperID", referencedColumnName = "shipID")
    private Shipper shipper;

    @ManyToOne
    @JoinColumn(name = "PayID", referencedColumnName = "payID")
    private Pays pays;  // Thuộc tính trỏ đến Pay

    // Constructor không tham số
    public Order() {
    }

    // Getters và Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
}

