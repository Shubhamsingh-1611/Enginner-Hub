package com.engineerhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.engineerhub.modals.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification,Long>{

}