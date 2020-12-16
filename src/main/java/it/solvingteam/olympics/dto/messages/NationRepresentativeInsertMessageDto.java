package it.solvingteam.olympics.dto.messages;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


public class NationRepresentativeInsertMessageDto {

	@NotEmpty(message = "The name is required")
	private String name;
	@NotEmpty(message = "The surname is required")
	private String surname;
	@NotEmpty(message = "The fiscal code is required")
	private String fiscalCode;

	@Valid
	private UserSignupMessageDto userSignupMessageDto;
	@NotEmpty(message = "Required field")
	private String nation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	public UserSignupMessageDto getUserSignupMessageDto() {
		return userSignupMessageDto;
	}
	public void setUserSignupMessageDto(UserSignupMessageDto userSignupMessageDto) {
		this.userSignupMessageDto = userSignupMessageDto;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	
	
}
