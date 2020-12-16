package it.solvingteam.olympics.dto.messages;

import javax.validation.constraints.NotEmpty;

public class UserSignupMessageDto {

	private String id;

    @NotEmpty(message = "Required field")
    private String username;

    @NotEmpty(message = "Required field")
    private String password;

    @NotEmpty(message = "Required field")
    private String repeatePassword;
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatePassword() {
        return repeatePassword;
    }

    public void setRepeatePassword(String repeatePassword) {
        this.repeatePassword = repeatePassword;
    }

}
