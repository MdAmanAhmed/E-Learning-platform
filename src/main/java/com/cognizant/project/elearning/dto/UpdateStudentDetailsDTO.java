package com.cognizant.project.elearning.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStudentDetailsDTO {
    @NotBlank(message = "College name is mandatory")
    private String college;
    @Min(value = 1, message = "Age should not be less than 1")
    @NotNull(message = "Age is mandatory")
    private Integer age;

}
