package com.cognizant.project.elearning_platform.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.StudentResponseDTO;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentRepository studentRepository;

    public StudentResponseDTO viewStudent(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentDetailNotFound("Student with Id " + studentId + " not found."));
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    public StudentResponseDTO updateStudentDetails(int studentId, String college, int age) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentDetailNotFound("Student with Id " + studentId + " not found."));
        student.setCollege(college);
        student.setAge(age);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }
}
