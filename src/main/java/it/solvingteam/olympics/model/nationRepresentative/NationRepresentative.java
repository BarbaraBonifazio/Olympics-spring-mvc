package it.solvingteam.olympics.model.nationRepresentative;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.solvingteam.olympics.model.Person;
import it.solvingteam.olympics.model.athlete.Athlete;
import it.solvingteam.olympics.model.nation.Nation;
import it.solvingteam.olympics.model.user.User;

@Entity
public class NationRepresentative extends Person{
	
	@OneToOne
    @JoinColumn(name = "nation_id", referencedColumnName = "id")
	private Nation nation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nationRepresentative", orphanRemoval = true)
	private Set<Athlete> athletes = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
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
	
}
