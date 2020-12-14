package it.solvingteam.olympics.dto.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SportDto {

	@NotNull(message = "The id doesn't exist")
	private String id;
	@NotEmpty(message = "Required field")
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
