package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.engineerhub.modals.Message;

public interface MessageRepository
        extends JpaRepository<Message,Long>{

}