package it.solvingteam.course.springmvc.springmvcdemo.dto.messages;

import javax.validation.constraints.NotNull;

public class CustomerDeleteMessageDto {

	
	@NotNull(message = "The id Customer Id doesn't exist")
    private String idCustomerDelete;
	
	public String getIdCustomerDelete() {
		return idCustomerDelete;
	}

	public void setIdCustomerDelete(String idCustomerDelete) {
		this.idCustomerDelete = idCustomerDelete;
	}
	
	
}
