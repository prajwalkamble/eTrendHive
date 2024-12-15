package com.etrendhive.service.implementation;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import com.etrendhive.exception.OrderException;
import com.etrendhive.model.Address;
import com.etrendhive.model.Cart;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.Order;
import com.etrendhive.model.OrderItem;
import com.etrendhive.model.User;
import com.etrendhive.repository.AddressRepository;
import com.etrendhive.repository.CartRepository;
import com.etrendhive.repository.OrderItemRepository;
import com.etrendhive.repository.OrderRepository;
import com.etrendhive.repository.UserRepository;
import com.etrendhive.service.CartService;
import com.etrendhive.service.OrderItemService;
import com.etrendhive.service.OrderService;
import com.etrendhive.service.ProductService;
import com.etrendhive.user.OrderStatus;
import com.etrendhive.user.PaymentStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImplementation implements OrderService{
	
	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address= addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem> orderItems=new ArrayList<>();
		
		for(CartItem item: cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
	
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus(OrderStatus.PENDING);
		createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		System.out.println("jhbbskabcdlclsd");
		return savedOrder;
		
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id "+orderId);
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		List<Order> orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.PLACED);
		order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.SHIPPED);
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.DELIVERED);
		return orderRepository.save(order);
	}

	@Override
	public Order cancelledOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order =findOrderById(orderId);
		
		orderRepository.deleteById(orderId);
		
	}

}
