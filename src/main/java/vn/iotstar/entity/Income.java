package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Income")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "Value", nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "BranchID", referencedColumnName = "branchID", nullable = false)
    private Branch branch;

    // Constructor, Getters, and Setters
}