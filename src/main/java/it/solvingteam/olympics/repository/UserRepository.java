package it.solvingteam.olympics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.olympics.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 Optional<User> findByUsername(String username);
	
}
