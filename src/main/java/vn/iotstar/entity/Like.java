package vn.iotstar.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeID;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "MilkTeaID", referencedColumnName = "milkTeaID")
    private MilkTea milkTea;

    @Column(name = "LikedTime")
    private LocalDate likedTime;  // Thời gian người dùng thích sản phẩm

    // Constructor không tham số
    public Like() {
    }

    // Getters and Setters
    public int getLikeID() {
        return likeID;
    }

    public void setLikeID(int likeID) {
        this.likeID = likeID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MilkTea getMilkTea() {
        return milkTea;
    }

    public void setMilkTea(MilkTea milkTea) {
        this.milkTea = milkTea;
    }

    public LocalDate getLikedTime() {
        return likedTime;
    }

    public void setLikedTime(LocalDate likedTime) {
        this.likedTime = likedTime;
    }
}
