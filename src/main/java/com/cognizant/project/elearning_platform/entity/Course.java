package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
//@Table
public class Course {
	@Id
private int courseId;
private String title;
private String description;
private String contentURL;

@ManyToOne    //check this it is not bi-directional for now. should make it bi ??
private Instructor instructorId;
}
