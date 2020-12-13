package it.solvingteam.course.springmvc.springmvcdemo.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerDeleteMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.model.Customer;
import it.solvingteam.course.springmvc.springmvcdemo.service.CustomerService;

@Component
public class CustomerDeleteMessageValidator implements Validator{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerDeleteMessageDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerDeleteMessageDto customerDeleteMessageDto = (CustomerDeleteMessageDto) target;
			Customer customer = customerService.getCustomerEntityFromCustomerDeleteMessageDto(customerDeleteMessageDto);
        if (customer.getDeliveries().size() > 0) {
            errors.rejectValue("idCustomerDelete", "customerDeleteModelhasDeliveriest", "This customer has deliveries! You can't delete it!");
        }
	}
	
	
	
}
