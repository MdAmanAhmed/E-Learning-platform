package com.cognizant.project.elearning_platform.entity;

import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;



import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Instructor extends User{
		private int salary;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="instructorId")
//	@JsonIgnore(if Entity class is directly displayed in Json at postman )
	//instead we used separate response DTO it avoid recursion.

	private List<Course> course;
	

	@OneToMany(mappedBy="instructorId" ,cascade=CascadeType.ALL,orphanRemoval=true)

	private List<Notification> notification;
}






/*@Entity
@Data
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int instructorId;
	private String name;
	private String password;
	private String email;
	private String role;
}*/
