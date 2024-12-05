package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID;
	@Column(columnDefinition = "nvarchar(max)")
	private String shipAddress;
	
	@ManyToOne
	@JoinColumn(name = "UserID", referencedColumnName = "userID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "CartID", referencedColumnName = "cartID")
	private Cart cart; // Một đơn hàng thuộc về một giỏ hàng

	@ManyToOne
	@JoinColumn(name = "BranchID", referencedColumnName = "branchID")
	private Branch branch;

	@ManyToOne
	@JoinColumn(name = "StatusID", referencedColumnName = "statusID")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "ShipperID", referencedColumnName = "shipID")
	private Shipper shipper;

	@ManyToOne
	@JoinColumn(name = "PayID", referencedColumnName = "payID")
	private Pays pays; // Thuộc tính trỏ đến Pay

	@Override
	public String toString() {
		return "Order{" + "orderID=" + orderID + ", userID=" + (user != null ? user.getUserID() : "null") + '}';
	}
}
