package it.solvingteam.olympics.dto.messages;

public class NationRepresentativeDto extends PersonDto{

	private AthleteDto athleteDto;
	private UserDto userDto;
	
	private NationDto nationDto;

	public AthleteDto getAthleteDto() {
		return athleteDto;
	}

	public void setAthleteDto(AthleteDto athleteDto) {
		this.athleteDto = athleteDto;
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
