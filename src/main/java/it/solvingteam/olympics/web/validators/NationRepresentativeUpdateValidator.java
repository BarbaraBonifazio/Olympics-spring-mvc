package it.solvingteam.olympics.web.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.olympics.dto.NationRepresentativeDto;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.service.NationRepresentativeService;

@Component
public class NationRepresentativeUpdateValidator implements Validator{

	@Autowired
	NationRepresentativeService nationRepresentativeService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NationRepresentativeDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NationRepresentativeDto nationRepresentativeDto = (NationRepresentativeDto) target;
		
		List<NationRepresentative> nationRepresentativesList = nationRepresentativeService.findAll();
		for(NationRepresentative nationRepresentativeFromDb : nationRepresentativesList){
		        if (nationRepresentativeFromDb.getNation().getId().equals(Long.parseLong(nationRepresentativeDto.getNationDto().getId())) &&
		        		!nationRepresentativeFromDb.getId().equals(Long.parseLong(nationRepresentativeDto.getId()))) {
		            errors.rejectValue("nationDto", "nationHasARepresentative", "This Nation already has a Representative! ");
		        }
		}
	}

}
