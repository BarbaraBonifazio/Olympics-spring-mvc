package it.solvingteam.olympics.model.enrollment;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.solvingteam.olympics.model.athlete.Athlete;
import it.solvingteam.olympics.model.competition.Competition;

@Entity
public class Enrollment {

	private Long id;
	private Integer score;
	private Boolean isEnrolled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "competition_id", nullable = false)
	private Competition competition;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "athlete_id", nullable = false)
	private Athlete athlete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsEnrolled() {
		return isEnrolled;
	}

	public void setIsEnrolled(Boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	
}
