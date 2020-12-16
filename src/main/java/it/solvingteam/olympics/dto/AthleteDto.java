package it.solvingteam.olympics.dto;

import javax.validation.constraints.NotNull;

public class AthleteDto extends PersonDto{

	@NotNull(message = "Required field")
	private String totalScore;
	@NotNull(message = "Required field")
	private String goldMedals;
	@NotNull(message = "Required field")
	private String silverMedals;
	@NotNull(message = "Required field")
	private String bronzeMedals;
	
	private UserDto userDto;
	private NationRepresentativeDto nationRepresentativeDto;
	
	
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getGoldMedals() {
		return goldMedals;
	}
	public void setGoldMedals(String goldMedals) {
		this.goldMedals = goldMedals;
	}
	public String getSilverMedals() {
		return silverMedals;
	}
	public void setSilverMedals(String silverMedals) {
		this.silverMedals = silverMedals;
	}
	public String getBronzeMedals() {
		return bronzeMedals;
	}
	public void setBronzeMedals(String bronzeMedals) {
		this.bronzeMedals = bronzeMedals;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public NationRepresentativeDto getNationRepresentativeDto() {
		return nationRepresentativeDto;
	}
	public void setNationRepresentativeDto(NationRepresentativeDto nationRepresentativeDto) {
		this.nationRepresentativeDto = nationRepresentativeDto;
	}
	
}
