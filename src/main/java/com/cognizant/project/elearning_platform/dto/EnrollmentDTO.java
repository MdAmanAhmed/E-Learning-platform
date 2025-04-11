package com.cognizant.project.elearning_platform.dto;


import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Student;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
//@Data
public class EnrollmentDTO {
	private int enrollmentId;
	@NotNull(message="studentId cant be null")
	private Student studentId;
	@NotNull(message="courseId cant be null")
	private Course courseId;
	
	private int progress;

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}

	public Course getCourseId() {
		return courseId;
	}

	public void setCourseId(Course courseId) {
		this.courseId = courseId;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}