package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engineerhub.modals.Product;

public interface ProductRepository
        extends JpaRepository<Product,Long>{

}