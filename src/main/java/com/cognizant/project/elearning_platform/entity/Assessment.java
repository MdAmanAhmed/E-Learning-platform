package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Assessment {
	@Id
	private int assessmentId;
	@ManyToOne
	private Course courseId;
	private String type;
	private int maxScore;
}
