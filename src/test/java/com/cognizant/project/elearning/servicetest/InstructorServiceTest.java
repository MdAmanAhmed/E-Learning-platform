package com.cognizant.project.elearning.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cognizant.project.elearning.dto.InstructorResponseDTO;
import com.cognizant.project.elearning.entity.Instructor;
import com.cognizant.project.elearning.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning.repository.InstructorRepository;
import com.cognizant.project.elearning.service.InstructorService;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    private InstructorResponseDTO instructorDTO;
    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        instructorDTO = new InstructorResponseDTO();
        instructorDTO.setUserId(1);
        instructorDTO.setName("Jane Doe");
        instructorDTO.setEmail("jane.doe@example.com");
        instructorDTO.setPassword("password123");

        instructor = new Instructor();
        instructor.setUserId(1);
        instructor.setName("Jane Doe");
        instructor.setEmail("jane.doe@example.com");
        instructor.setPassword("password123");
    }

    @Test
    public void testAddInstructor() {
        when(modelMapper.map(instructorDTO, Instructor.class)).thenReturn(instructor);
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);
        when(modelMapper.map(instructor, InstructorResponseDTO.class)).thenReturn(instructorDTO);

        InstructorResponseDTO result = instructorService.addInstructor(instructorDTO);

        assertEquals(instructorDTO, result);
    }

    @Test
    public void testRemoveInstructor_Success() {
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));

        String result = instructorService.removeInstructor(1);

        verify(instructorRepository).delete(instructor);
        assertEquals("successful", result);
    }

    @Test
    public void testRemoveInstructor_Failed() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        String result = instructorService.removeInstructor(1);

        assertEquals("failed no one", result);
    }

    @Test
    public void testViewInstructor_Success() {
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
        when(modelMapper.map(instructor, InstructorResponseDTO.class)).thenReturn(instructorDTO);

        InstructorResponseDTO result = instructorService.viewInstructor(1);

        assertEquals(instructorDTO, result);
    }

    @Test
    public void testViewInstructor_NotFound() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InstructorDetailNotFound.class, () -> {
            instructorService.viewInstructor(1);
        });
    }
}
