package com.cognizant.project.elearning_platform.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Assessment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int assessmentId;
	
	private String type;
	
	private int maxScore;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="courseId")
	private Course courseId;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="assessmentId")
	@JsonIgnore
	private List<Submission> submission;
}
