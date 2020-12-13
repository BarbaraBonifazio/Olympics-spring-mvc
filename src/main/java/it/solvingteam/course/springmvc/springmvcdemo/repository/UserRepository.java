package it.solvingteam.course.springmvc.springmvcdemo.repository;


import it.solvingteam.course.springmvc.springmvcdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
