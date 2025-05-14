package com.cognizant.project.elearning.entity;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;

=======
>>>>>>> 95b06771d3c09b52b491048255dfd219994f9c9f
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
