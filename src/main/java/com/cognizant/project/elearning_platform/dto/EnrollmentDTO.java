package com.cognizant.project.elearning_platform.dto;


import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Student;

import lombok.Data;
@Data
public class EnrollmentDTO {
	private int enrollmentId;
	private Student studentId;
	private Course courseId;
	private int progress;
}