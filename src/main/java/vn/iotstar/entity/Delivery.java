package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryID;

    @Column(name = "DeliveryName", length = 255)  // Specifying length for DeliveryName
    private String deliveryName;

    @Column(name = "DeliveryType", length = 255)  // Specifying length for DeliveryType
    private String deliveryType;

    @Column(name = "ExtraCost")
    private BigDecimal extraCost;  // Change from double to BigDecimal for precision

    // Mối quan hệ với Shipper (1:N)
    @OneToMany(mappedBy = "delivery")
    private List<Shipper> shippers;

    // Constructor không tham số
    public Delivery() {
    }

    // Getters và Setters
    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(BigDecimal extraCost) {
        this.extraCost = extraCost;
    }

    public List<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(List<Shipper> shippers) {
        this.shippers = shippers;
    }
}
