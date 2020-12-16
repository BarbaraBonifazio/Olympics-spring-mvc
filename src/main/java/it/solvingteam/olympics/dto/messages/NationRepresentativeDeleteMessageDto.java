package it.solvingteam.olympics.dto.messages;

import javax.validation.constraints.NotNull;

public class NationRepresentativeDeleteMessageDto {


	@NotNull(message = "The Nation Representative Id doesn't exist")
    private String idNationRepresentativeDelete;
	
	public String getIdNationRepresentativeDelete() {
		return idNationRepresentativeDelete;
	}

	public void setIdNationRepresentativeDelete(String idNationRepresentativeDelete) {
		this.idNationRepresentativeDelete = idNationRepresentativeDelete;
	}
	
}
