package it.solvingteam.olympics.model.competition;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.solvingteam.olympics.model.enrollment.Enrollment;
import it.solvingteam.olympics.model.sport.Sport;

@Entity
public class Competition {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer numberAthlets;
	private LocalDate competitionDate;
	private Integer scoreModifier;
	
	@Enumerated(EnumType.STRING)
	private CompetitionStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id", nullable = false)
	private Sport sport;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competition", orphanRemoval = true)
	private Set<Enrollment> enrollments = new TreeSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberAthlets() {
		return numberAthlets;
	}

	public void setNumberAthlets(Integer numberAthlets) {
		this.numberAthlets = numberAthlets;
	}

	public LocalDate getCompetitionDate() {
		return competitionDate;
	}

	public void setCompetitionDate(LocalDate competitionDate) {
		this.competitionDate = competitionDate;
	}

	public Integer getScoreModifier() {
		return scoreModifier;
	}

	public void setScoreModifier(Integer scoreModifier) {
		this.scoreModifier = scoreModifier;
	}

	public CompetitionStatus getStatus() {
		return status;
	}

	public void setStatus(CompetitionStatus status) {
		this.status = status;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
