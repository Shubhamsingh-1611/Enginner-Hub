package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.engineerhub.modals.Order;


public interface OrderRepository
        extends JpaRepository<Order,Long>{

}