package it.solvingteam.olympics.dto;

import javax.validation.constraints.NotNull;

public class NationDto {
	
	@NotNull(message = "The id doesn't exist")
	private String id;
	private String name;
	
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
}
