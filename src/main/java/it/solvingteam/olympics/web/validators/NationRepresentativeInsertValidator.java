package it.solvingteam.olympics.web.validators;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.service.NationRepresentativeService;

@Component
public class NationRepresentativeInsertValidator implements Validator{

	@Autowired
    private NationRepresentativeService nationRepresentativeService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return NationRepresentativeInsertMessageDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto = (NationRepresentativeInsertMessageDto) target;

		List<NationRepresentative> nationRepresentativesList = nationRepresentativeService.findAll();
		for(NationRepresentative nationRepresentative : nationRepresentativesList){
	        if (nationRepresentativeInsertMessageDto.getNation().equals(nationRepresentative.getNation().getId().toString())) {
	            errors.rejectValue("nation", "nationHasARepresentative", "This Nation already has a Representative! ");
	        }
		}
	}

	
}
