package com.cognizant.project.elearning.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Notification {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	private LocalDateTime dateTime;

	@ManyToMany
	@JoinTable(name = "notified_students",
	joinColumns = @JoinColumn(name = "notification_id"),
	inverseJoinColumns = @JoinColumn(name = "student_id")
												)
	private List<Student> studentId;
	@ManyToOne
	@JoinColumn(name="InstructorId")
	private Instructor instructorId;
	@ManyToOne
	@JoinColumn(name="courseId")
	private Course courseId;
		
		
}
