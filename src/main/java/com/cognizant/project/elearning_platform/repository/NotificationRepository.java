package com.cognizant.project.elearning_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.project.elearning_platform.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>  {

}
