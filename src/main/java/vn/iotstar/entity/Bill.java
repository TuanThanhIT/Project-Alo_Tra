package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billID;

    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "orderID")
    private Order order;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Cost", precision = 18, scale = 0)
    private BigDecimal cost;

    @Column(name = "ShipFee", precision = 18, scale = 0)
    private BigDecimal shipFee;

    @Column(name = "Total", precision = 18, scale = 0)
    private BigDecimal total;

    // Constructor không tham số
    public Bill() {
    }

    // Getter và Setter
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getShipFee() {
        return shipFee;
    }

    public void setShipFee(BigDecimal shipFee) {
        this.shipFee = shipFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
