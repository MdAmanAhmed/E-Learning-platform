package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Assessment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int assessmentId;
	private String type;
	private int maxScore;
	@ManyToOne
	private Course courseId;
}
