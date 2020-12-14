package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.athlete.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete, Integer>{

}
