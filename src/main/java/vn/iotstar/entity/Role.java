package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;

    @Column(name = "RoleName")
    private String roleName;

    // Mối quan hệ 1:N với User
    @OneToMany(mappedBy = "role")  // mappedBy trỏ đến thuộc tính "role" trong lớp User
    private List<User> users;

    // Constructor không tham số
    public Role() {
    }

    // Getters và Setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
