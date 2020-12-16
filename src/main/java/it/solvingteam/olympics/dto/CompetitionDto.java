package it.solvingteam.olympics.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CompetitionDto {

	@NotNull(message = "The id doesn't exist")
	private String id;
	@NotEmpty(message = "Required field")
	private String name;
	
	private SportDto sportDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SportDto getSportDto() {
		return sportDto;
	}
	public void setSportDto(SportDto sportDto) {
		this.sportDto = sportDto;
	}
	
}
