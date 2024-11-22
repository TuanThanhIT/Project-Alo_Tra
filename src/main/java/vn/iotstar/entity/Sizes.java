package vn.iotstar.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;  // Thêm import BigDecimal
import java.util.List;

@Entity
@Table(name = "Sizes")
public class Sizes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeID;

    @Column(name = "SizeName", nullable = false, columnDefinition = "nvarchar(max)")
    private String sizeName;

    @Column(name = "ExtraCost", nullable = false, precision = 18, scale = 2) // Sửa precision và scale
    private BigDecimal extraCost;  // Thay đổi kiểu dữ liệu từ double sang BigDecimal

    // Mối quan hệ 1:N với MilkTea
    @OneToMany(mappedBy = "size")
    private List<MilkTea> milkTeas;

    // Constructor không tham số
    public Sizes() {
    }

    // Getters và Setters
    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public BigDecimal getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(BigDecimal extraCost) {
        this.extraCost = extraCost;
    }

    public List<MilkTea> getMilkTeas() {
        return milkTeas;
    }

    public void setMilkTeas(List<MilkTea> milkTeas) {
        this.milkTeas = milkTeas;
    }
}
