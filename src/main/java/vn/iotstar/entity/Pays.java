package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Pays")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payID;

    @Column(name = "PayName", columnDefinition = "nvarchar(255)")
    private String payName;

    // Mối quan hệ 1:N với Order
    @OneToMany(mappedBy = "pays") // mappedBy phải trỏ đến thuộc tính "pays" trong lớp Order
    private List<Order> orders;
}

