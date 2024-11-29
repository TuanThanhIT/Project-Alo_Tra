package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CityID", referencedColumnName = "cityID")
    private City city;

    @Column(name = "Images")
    private String images;

    @Column(name = "Address")
    private String address;

    @Column(name = "Description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(columnDefinition = "nvarchar(255)")
    private String introduction;
    
    @Column(name = "OpenTime")
    private LocalTime openTime;

    @Column(name = "CloseTime")
    private LocalTime closeTime;

    @Column(name = "Active")
    private int active;

    @Column(name = "Income")
    private BigDecimal income;

    @ManyToOne
    @JoinColumn(name = "RateID", referencedColumnName = "rateID")
    private Rate rate;

    // Mối quan hệ N:M với MilkTea thông qua bảng trung gian
    @ManyToMany
    @JoinTable(
        name = "Branch_MilkTea",  // Tên bảng trung gian
        joinColumns = @JoinColumn(name = "BranchID"),
        inverseJoinColumns = @JoinColumn(name = "MilkTeaID")
    )
    private List<MilkTea> milkTeas;

    // Các getter và setter khác

    public List<MilkTea> getMilkTeas() {
        return milkTeas;
    }

    public void setMilkTeas(List<MilkTea> milkTeas) {
        this.milkTeas = milkTeas;
    }
}
