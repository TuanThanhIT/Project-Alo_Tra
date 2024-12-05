package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Shipper")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}

