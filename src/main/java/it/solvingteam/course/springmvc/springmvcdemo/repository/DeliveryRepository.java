package it.solvingteam.course.springmvc.springmvcdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.course.springmvc.springmvcdemo.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer>{

	
}
