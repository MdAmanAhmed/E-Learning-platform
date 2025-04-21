package com.cognizant.project.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.project.elearning.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>  {

}
