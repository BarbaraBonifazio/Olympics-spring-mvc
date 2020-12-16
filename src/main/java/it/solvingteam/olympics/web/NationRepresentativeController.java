package it.solvingteam.olympics.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.solvingteam.olympics.dto.NationRepresentativeDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeDeleteMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.olympics.dto.messages.UserSigninMessageDto;
import it.solvingteam.olympics.service.NationRepresentativeService;
import it.solvingteam.olympics.service.NationService;
import it.solvingteam.olympics.service.UserService;
import it.solvingteam.olympics.web.validators.UserSignupMessageValidator;

@Controller
@EnableWebSecurity
@RequestMapping("nationRepresentative")
public class NationRepresentativeController {
	
	@Autowired
	NationRepresentativeService nationRepresentativeService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	NationService nationService;
	
    @Autowired
    private UserSignupMessageValidator userSignupMessageValidator;
    
    @Secured("ROLE_ORGANIZZATORE")
    @GetMapping("registerRepresentationOrganizzatore")
	public String registerRepresentationOrganizzatore(NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, Model model, 
						NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {
		List<NationRepresentativeDto> allNationRepresentatives = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

		model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
		model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
		model.addAttribute("nationRepresentativeSearchNation", nationService.findAll());
		model.addAttribute("nationRepresentatives", allNationRepresentatives);

		return "nationRepresentative/registerRepresentation";
	}
    
//    	System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority());
    	
  
    
  //mapping per la creazione del rappresentante nazione
    @Secured("ROLE_ORGANIZZATORE")
    @GetMapping("signup")
    public String signup(Model model) {
    	model.addAttribute("nationRepresentativeInsertModel", new NationRepresentativeInsertMessageDto());
    	model.addAttribute("nationRepresentativeInsertNation", nationService.findAll());
        return "nationRepresentative/signup";
    }

    @Secured("ROLE_ORGANIZZATORE")
    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute("nationRepresentativeInsertModel") NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto, 
									BindingResult bindingResult, Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {
    	
    	userSignupMessageValidator.validate(nationRepresentativeInsertMessageDto.getUserSignupMessageDto(), bindingResult);

        if (bindingResult.hasErrors()) {
        	model.addAttribute("nationRepresentativeInsertNation", nationService.findAll());
            return "nationRepresentative/signup";
        } else {
        	
            userService.signupNationRepresentative(nationRepresentativeInsertMessageDto);
            model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
            nationRepresentativeService.insert(nationRepresentativeInsertMessageDto);
            return "nationRepresentative/registerRepresentation";
        }
    }

    @GetMapping("signin")
    public String signin(Model model) {
        model.addAttribute("userSigninModel", new UserSigninMessageDto());
        return "nationRepresentative/signin";
    }
    
    @GetMapping("show/{id}")
	public String show(@PathVariable Long id, Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, 
						NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {
		if (id != null) {
			NationRepresentativeShowDto nationeRepresentativeDtoShow = nationRepresentativeService.NationRepresentativeEntityToNationRepresentativeDto(id);
			model.addAttribute("nationRepresentativeShow", nationeRepresentativeDtoShow);
		} else {
			List<NationRepresentativeDto> allNationRepresentatives = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

			model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationRepresentativeSearchNation", nationService.findAll());
			model.addAttribute("nationRepresentatives", allNationRepresentatives);
			return "redirect:/registerRepresentationOrganizzatore";
		}
		return "nationRepresentative/show";
	}
	
}
