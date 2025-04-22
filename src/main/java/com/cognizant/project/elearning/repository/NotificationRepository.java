package com.cognizant.project.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>  {

}
