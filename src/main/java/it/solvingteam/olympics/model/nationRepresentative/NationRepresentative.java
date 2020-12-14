package it.solvingteam.olympics.model.nationRepresentative;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.solvingteam.olympics.model.Person;
import it.solvingteam.olympics.model.Role;
import it.solvingteam.olympics.model.athlete.Athlete;
import it.solvingteam.olympics.model.nation.Nation;
import it.solvingteam.olympics.model.user.User;

@Entity
public class NationRepresentative extends Person{
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nation_id", referencedColumnName = "id")
	private Nation nation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nationRepresentative", orphanRemoval = true)
	private Set<Athlete> athletes = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Set<Athlete> getAthletes() {
		return athletes;
	}

	public void setAthletes(Set<Athlete> athletes) {
		this.athletes = athletes;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
