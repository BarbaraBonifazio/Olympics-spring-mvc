package it.solvingteam.olympics.model.athlete;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.solvingteam.olympics.model.Person;
import it.solvingteam.olympics.model.Role;
import it.solvingteam.olympics.model.enrollment.Enrollment;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.model.sport.Sport;
import it.solvingteam.olympics.model.user.User;

@Entity
public class Athlete extends Person {

	private Integer totalScore;
	private Integer goldMedals;
	private Integer silverMedals;
	private Integer bronzeMedals;

	@Enumerated(EnumType.STRING)
	private AthleteStatus status;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nationRepresentative_id", nullable = false)
	private NationRepresentative nationRepresentative;

	@ManyToMany
	@JoinTable(name = "athlete_sport", joinColumns = @JoinColumn(name = "athlete_id", referencedColumnName = "ID"), 
				inverseJoinColumns = @JoinColumn(name = "sport_id", referencedColumnName = "ID"))
	private Set<Sport> sports = new HashSet<Sport>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "athlete", orphanRemoval = true)
	private Set<Enrollment> enrollments = new HashSet<>();

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getGoldMedals() {
		return goldMedals;
	}

	public void setGoldMedals(Integer goldMedals) {
		this.goldMedals = goldMedals;
	}

	public Integer getSilverMedals() {
		return silverMedals;
	}

	public void setSilverMedals(Integer silverMedals) {
		this.silverMedals = silverMedals;
	}

	public Integer getBronzeMedals() {
		return bronzeMedals;
	}

	public void setBronzeMedals(Integer bronzeMedals) {
		this.bronzeMedals = bronzeMedals;
	}

	public AthleteStatus getStatus() {
		return status;
	}

	public void setStatus(AthleteStatus status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NationRepresentative getNationRepresentative() {
		return nationRepresentative;
	}

	public void setNationRepresentative(NationRepresentative nationRepresentative) {
		this.nationRepresentative = nationRepresentative;
	}

	public Set<Sport> getSports() {
		return sports;
	}

	public void setSports(Set<Sport> sports) {
		this.sports = sports;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
