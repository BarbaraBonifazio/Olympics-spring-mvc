package it.solvingteam.course.springmvc.springmvcdemo.repository;

import it.solvingteam.course.springmvc.springmvcdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
