package it.solvingteam.olympics.dto.messages;

import java.util.Set;

import it.solvingteam.olympics.dto.AthleteDto;
import it.solvingteam.olympics.dto.NationDto;
import it.solvingteam.olympics.dto.PersonDto;
import it.solvingteam.olympics.dto.UserDto;

public class NationRepresentativeShowDto extends PersonDto{

	private Set<AthleteDto> athleteDtoList;
	private UserDto userDto;
	private NationDto nationDto;
	
	public Set<AthleteDto> getAthleteDtoList() {
		return athleteDtoList;
	}
	public void setAthleteDtoList(Set<AthleteDto> athleteDtoList) {
		this.athleteDtoList = athleteDtoList;
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
