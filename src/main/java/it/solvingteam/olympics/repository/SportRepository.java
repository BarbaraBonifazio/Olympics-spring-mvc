package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.sport.Sport;

public interface SportRepository extends JpaRepository<Sport, Long>{

}
