package it.solvingteam.olympics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.nation.Nation;

public interface NationRepository extends JpaRepository<Nation, Long>{

}
