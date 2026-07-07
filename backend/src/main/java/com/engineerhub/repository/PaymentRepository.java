package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.engineerhub.modals.Payment;
public interface PaymentRepository
        extends JpaRepository<Payment,Long>{

}