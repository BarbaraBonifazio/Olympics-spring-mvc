package it.solvingteam.olympics.dto;

import java.util.Set;

import javax.validation.Valid;

public class NationRepresentativeDto extends PersonDto{

	private Set<AthleteDto> athletesDtoList;
	private UserDto userDto;
	@Valid
	private NationDto nationDto;

	public Set<AthleteDto> getAthletesDtoList() {
		return athletesDtoList;
	}

	public void setAthletesDtoList(Set<AthleteDto> athletesDtoList) {
		this.athletesDtoList = athletesDtoList;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public NationDto getNationDto() {
		return nationDto;
	}

	public void setNationDto(NationDto nationDto) {
		this.nationDto = nationDto;
	}
	
}
