package it.solvingteam.olympics.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.olympics.dto.messages.NationRepresentativeDeleteMessageDto;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.service.NationRepresentativeService;

@Component
public class NationRepresentativeDeleteValidator implements Validator{
	
	@Autowired
	NationRepresentativeService nationRepresentativeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return NationRepresentativeDeleteMessageDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto = (NationRepresentativeDeleteMessageDto) target;
		NationRepresentative nationRepresentative = 
					nationRepresentativeService.getNationReprEntityFromNationReprDeleteMessageDto(nationRepresentativeDeleteMessageDto);
		
        if (nationRepresentative.getAthletes().size() > 0) {
            errors.rejectValue("idNationRepresentativeDelete", "This Nation Representative has registered athletes! You can't delete the record!");
        }
	}

}
