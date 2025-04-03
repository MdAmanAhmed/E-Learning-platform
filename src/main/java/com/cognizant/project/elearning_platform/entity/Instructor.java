package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends User {

}
