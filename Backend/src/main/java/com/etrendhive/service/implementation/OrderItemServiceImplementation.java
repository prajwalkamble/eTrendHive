package com.etrendhive.service.implementation;

import org.springframework.stereotype.Service;

import com.etrendhive.model.OrderItem;
import com.etrendhive.repository.OrderItemRepository;
import com.etrendhive.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
	
	private OrderItemRepository orderItemRepository;
	
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
