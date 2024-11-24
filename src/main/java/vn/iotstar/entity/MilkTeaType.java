package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "MilkTeaType")
public class MilkTeaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int milkTeaTypeID;

    @Column(name = "MilkTeaTypeName")
    private String milkTeaTypeName;

    // Mối quan hệ 1:N với MilkTea
    @OneToMany(mappedBy = "milkTeaType")
    private List<MilkTea> milkTeas;

    // Constructor không tham số
    public MilkTeaType() {
    }

    @Override
    public String toString() {
        return "MilkTeaType{" +
               "milkTeaTypeID=" + milkTeaTypeID +
               ", milkTeaTypeName='" + milkTeaTypeName + '\'' +
               '}';
    }
    // Getters và Setters
    public int getMilkTeaTypeID() {
        return milkTeaTypeID;
    }

    public void setMilkTeaTypeID(int milkTeaTypeID) {
        this.milkTeaTypeID = milkTeaTypeID;
    }

    public String getMilkTeaTypeName() {
        return milkTeaTypeName;
    }

    public void setMilkTeaTypeName(String milkTeaTypeName) {
        this.milkTeaTypeName = milkTeaTypeName;
    }

    public List<MilkTea> getMilkTeas() {
        return milkTeas;
    }

    public void setMilkTeas(List<MilkTea> milkTeas) {
        this.milkTeas = milkTeas;
    }
}
