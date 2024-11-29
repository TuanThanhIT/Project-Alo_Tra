package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discountID;

    @Column(name = "PercentDiscount")
    private BigDecimal percentDiscount;  // Use BigDecimal for precision

    @Column(name = "ApplyTime")
    private LocalTime applyTime;  // Use LocalTime for time without date

    @Column(name = "EndTime")
    private LocalTime endTime;  // Use LocalTime for time without date

    @Column(name = "ApplyDay")
    private LocalDate applyDay;  // Use LocalDate for date

    // Mối quan hệ với MilkTea (1:N)
    @OneToMany(mappedBy = "discount")
    private List<MilkTea> milkTeas;

    // Constructor không tham số
    public Discount() {
    }

    // Getters và Setters
    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public BigDecimal getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(BigDecimal percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public LocalTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalTime applyTime) {
        this.applyTime = applyTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(LocalDate applyDay) {
        this.applyDay = applyDay;
    }

    public List<MilkTea> getMilkTeas() {
        return milkTeas;
    }

    public void setMilkTeas(List<MilkTea> milkTeas) {
        this.milkTeas = milkTeas;
    }
}
