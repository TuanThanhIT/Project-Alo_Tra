package vn.iotstar.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "FullName", nullable = false, columnDefinition = "nvarchar(max)")
    private String fullName;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "Address", nullable = false, columnDefinition = "nvarchar(max)")
    private String address;

    @Column(name = "Phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "Email", nullable = false, columnDefinition = "nvarchar(max)")
    private String email;

    @Column(name = "Username", nullable = false, columnDefinition = "nvarchar(max)")
    private String userName;

    @Column(name = "Password", nullable = false, columnDefinition = "nvarchar(max)")
    private String password;

    @Column(name = "Active", nullable = false)
    private boolean active;

    @Column(name = "Image", columnDefinition = "nvarchar(max)")
    private String image;

    @Column(name = "RoleID", nullable = false)
    private int roleID;

    
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    
    @OneToMany(mappedBy = "user")
    private List<Rate> rates;

    
    @OneToMany(mappedBy = "user")
    private List<Shipper> shippers;

    // Mối quan hệ với Role (N:1)
    @ManyToOne
    @JoinColumn(name = "RoleID", referencedColumnName = "roleID", insertable = false, updatable = false)
    private Role role;

    // Constructor không tham số
    public User() {
    }
    
    
    public User(String fullName, Date date, String address, String phone, String email, String userName,
			String password, boolean active, String image, int roleID) {
		this.fullName = fullName;
		this.date = date;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.image = image;
		this.roleID = roleID;
	}


	// Getters và Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Shipper> getShippers() {
        return shippers;
    }

    public void setShippers(List<Shipper> shippers) {
        this.shippers = shippers;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
