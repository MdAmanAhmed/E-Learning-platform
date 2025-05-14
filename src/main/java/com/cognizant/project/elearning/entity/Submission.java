package com.cognizant.project.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Submission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int submissionId;
	@ManyToOne
	@JoinColumn(name="assessmentId")
	private Assessment assessmentId;
	@ManyToOne
	@JoinColumn(name="studentId")
	private Student studentId;
	private int currentScore;
	private String answer;
	

}
