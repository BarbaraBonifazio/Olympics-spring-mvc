package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.competition.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{

}
