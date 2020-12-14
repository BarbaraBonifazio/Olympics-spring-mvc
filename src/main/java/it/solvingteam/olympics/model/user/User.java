package it.solvingteam.olympics.model.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.solvingteam.olympics.model.Person;
import it.solvingteam.olympics.model.Role;

@Entity
public class User extends Person{

	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
