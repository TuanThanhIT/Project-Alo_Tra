package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusID;

    @Column(name = "StatusName", nullable = false, columnDefinition = "nvarchar(255)")
    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;

    // Constructor không tham số
    
}