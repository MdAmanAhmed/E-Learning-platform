package com.cognizant.project.elearning_platform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Submission {
	@Id
private int submissionId;
	@ManyToOne
private Assessment assessmentId;
	@ManyToOne
private Student studentId;
private int score;
}
