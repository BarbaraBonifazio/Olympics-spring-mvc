package it.solvingteam.course.springmvc.springmvcdemo;

import it.solvingteam.course.springmvc.springmvcdemo.model.Role;
import it.solvingteam.course.springmvc.springmvcdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcDemoApplication.class, args);
    }

}
