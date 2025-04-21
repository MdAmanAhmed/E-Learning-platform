package com.cognizant.project.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning.entity.Submission;
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

}
