package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerhub.modals.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long>{

}
