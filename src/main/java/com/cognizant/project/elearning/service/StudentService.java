package com.cognizant.project.elearning.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.dto.StudentResponseDTO;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.StudentRepository;

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
