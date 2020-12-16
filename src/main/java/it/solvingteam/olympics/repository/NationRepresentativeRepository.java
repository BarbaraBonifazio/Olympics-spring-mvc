package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;

public interface NationRepresentativeRepository extends JpaRepository<NationRepresentative, Long>{


}
