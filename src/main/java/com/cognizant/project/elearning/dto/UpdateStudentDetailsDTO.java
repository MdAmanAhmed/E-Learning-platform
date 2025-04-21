package com.cognizant.project.elearning.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateStudentDetailsDTO {
    @NotBlank(message = "College name is mandatory")
    private String college;

    @Min(value = 1, message = "Age should not be less than 1")
    @NotNull(message = "Age is mandatory")
    private Integer age;

    // Getters and setters
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
