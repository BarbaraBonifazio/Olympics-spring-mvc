package it.solvingteam.course.springmvc.springmvcdemo.dto.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeliveryDto {

	@NotNull(message = "The id Customer Id doesn't exist")
    private String id;
	
	@NotEmpty(message = "The description is required")
	private String description;

	@NotEmpty(message = "The Delivery Date is required")
    private String shippingDate;

	@NotEmpty(message = "The price is required")
    private String price;
	
	private CustomerDto customerDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	
	
}
