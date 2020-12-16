package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.enrollment.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
