package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Shipper")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RateID", referencedColumnName = "rateID")
    private Rate rate;

    @Column(name = "DeliverTimes")
    private int deliverTimes;

    @ManyToOne
    @JoinColumn(name = "DeliveryID", referencedColumnName = "deliveryID")
    private Delivery delivery;

    // Constructor không tham số
    public Shipper() {
    }

    // Getters and Setters
    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public int getDeliverTimes() {
        return deliverTimes;
    }

    public void setDeliverTimes(int deliverTimes) {
        this.deliverTimes = deliverTimes;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
