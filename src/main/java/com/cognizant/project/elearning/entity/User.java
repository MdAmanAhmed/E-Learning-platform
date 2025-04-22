package com.cognizant.project.elearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="User", uniqueConstraints= {@UniqueConstraint(columnNames= {"email"})})
public  class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@Column(nullable=false,length=50)
	private String name;
	@Column(nullable=false,length=254)
	private String password;
	@Column(nullable=false,length=254)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role;
	
	 
}
