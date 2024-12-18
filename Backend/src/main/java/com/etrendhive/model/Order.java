package com.etrendhive.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import com.etrendhive.user.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
@ToString(exclude = "orderItems")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem> orderItems=new ArrayList<>();

	private LocalDateTime orderDate;
	
	private LocalDateTime deliveryDate;
	
	@OneToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalPrice;
	
	private Integer totalDiscountedPrice;
	
	private Integer discount;
	
	private OrderStatus orderStatus;
	
	private int totalItem;
	
	private LocalDateTime createdAt;
	
}
