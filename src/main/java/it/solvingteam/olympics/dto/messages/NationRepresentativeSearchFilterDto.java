package it.solvingteam.olympics.dto.messages;

public class NationRepresentativeSearchFilterDto {

	private String name;
	private String surname;
	private String fiscalCode;
	private UserSearchDto userSearchDto;
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
	public UserSearchDto getUserSearchDto() {
		return userSearchDto;
	}
	public void setUserSearchDto(UserSearchDto userSearchDto) {
		this.userSearchDto = userSearchDto;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	
}
