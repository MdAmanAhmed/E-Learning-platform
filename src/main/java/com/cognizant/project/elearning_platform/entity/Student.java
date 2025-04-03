package com.cognizant.project.elearning_platform.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;




@Entity
@DiscriminatorValue("Student")
public class Student extends User {

}