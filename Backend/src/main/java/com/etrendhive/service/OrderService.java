package com.etrendhive.service;

import java.util.*;

import com.etrendhive.exception.OrderException;
import com.etrendhive.model.Address;
import com.etrendhive.model.Order;
import com.etrendhive.model.User;

public interface OrderService {
	public Order createOrder(User user,Address shippingAddress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> userOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancelledOrder(Long orderId) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
	
	
}
