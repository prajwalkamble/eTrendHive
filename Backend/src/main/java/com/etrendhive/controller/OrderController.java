package com.etrendhive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etrendhive.exception.OrderException;
import com.etrendhive.exception.UserException;
import com.etrendhive.model.Address;
import com.etrendhive.model.Order;
import com.etrendhive.model.User;
import com.etrendhive.service.OrderService;
import com.etrendhive.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Order> createOrderHandler(@RequestBody Address shippingAddress,@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJWT(jwt);
		 
		Order order=orderService.createOrder(user, shippingAddress);
		 
		System.out.println("Order"+order);
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
		User user=userService.findUserProfileByJWT(jwt);
		List<Order> orders=orderService.userOrderHistory(user.getId());
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);	
	}
	
	@GetMapping("/id/{Id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("Id")Long orderId,@RequestHeader("Authorization") String jwt) throws UserException,OrderException{
		User user=userService.findUserProfileByJWT(jwt);
		
		Order order=orderService.findOrderById(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
		
	}

}
