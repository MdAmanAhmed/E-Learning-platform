package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role",discriminatorType=DiscriminatorType.STRING)
@Data
public abstract class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int userId;
private String name;
private String password;
private String email;
@Transient
private String role;

@PostLoad
private void setRole() {
    if (this instanceof Student) {
        this.role = "STUDENT";
    } else if (this instanceof Instructor) {
        this.role = "INSTRUCTOR";
    }
}}