package com.etrendhive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etrendhive.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

