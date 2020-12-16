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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.solvingteam.olympics.dto.NationRepresentativeDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeDeleteMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.olympics.model.user.User;
import it.solvingteam.olympics.service.NationRepresentativeService;
import it.solvingteam.olympics.service.NationService;
import it.solvingteam.olympics.service.UserService;
import it.solvingteam.olympics.web.validators.NationRepresentativeDeleteValidator;
import it.solvingteam.olympics.web.validators.NationRepresentativeInsertValidator;
import it.solvingteam.olympics.web.validators.NationRepresentativeUpdateValidator;
import it.solvingteam.olympics.web.validators.UserSignupMessageValidator;

@Controller
@EnableWebSecurity
@RequestMapping("organizer")
public class OrganizerController {
	
	@Autowired
	private NationRepresentativeService nationRepresentativeService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private NationService nationService;
	
    @Autowired
    private UserSignupMessageValidator userSignupMessageValidator;
    
    @Autowired
    private NationRepresentativeInsertValidator nationRepresentativeInsertValidator;
    
    @Autowired
    private NationRepresentativeDeleteValidator nationRepresentativeDeleteValidator;
    
    @Autowired
    private NationRepresentativeUpdateValidator nationRepresentativeUpdateValidator;
    
    @Secured("ROLE_ORGANIZZATORE")
    @GetMapping
	public String nationRepresentativeDashBoard(NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, Model model, 
						NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {
		List<NationRepresentativeDto> allNationRepresentatives = nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

		model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
		model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
		model.addAttribute("nationsList", nationService.findAll());
		model.addAttribute("nationRepresentatives", allNationRepresentatives);

		return "organizer/listNationRepresentatives";
	}
    
//    	System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority());
    	
    @Secured("ROLE_ORGANIZZATORE")
    @GetMapping("prepareInsertNationRepresentative")
    public String prepareInsertNationRepresentative(Model model) {
    	model.addAttribute("nationRepresentativeInsertModel", new NationRepresentativeInsertMessageDto());
    	model.addAttribute("nationsList", nationService.findAll());
        return "organizer/insertNationRepresentative";
    }

    @Secured("ROLE_ORGANIZZATORE")
    @PostMapping("executeInsertNationRepresentative")
    public String executeInsertNationRepresentative(@Valid @ModelAttribute("nationRepresentativeInsertModel") NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto, 
									BindingResult bindingResult, Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto,
									NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto, RedirectAttributes redirectAttributes) {
    	
    	userSignupMessageValidator.validate(nationRepresentativeInsertMessageDto.getUserSignupMessageDto(), bindingResult);
    	nationRepresentativeInsertValidator.validate(nationRepresentativeInsertMessageDto, bindingResult);

        if (bindingResult.hasErrors()) {
        	model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
            model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
        	model.addAttribute("nationsList", nationService.findAll());
            return "organizer/insertNationRepresentative";
        } else {	
            userService.insertUserNationRepresentative(nationRepresentativeInsertMessageDto);
            model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
            model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
            model.addAttribute("nationsList", nationService.findAll());
            nationRepresentativeService.insert(nationRepresentativeInsertMessageDto);
            redirectAttributes.addFlashAttribute("successMessage", "Insert Nation Representative Successful!");
            return "redirect:/organizer";
        }
    }

//    @GetMapping("signin")
//    public String signin(Model model) {
//        model.addAttribute("userSigninModel", new UserSigninMessageDto());
//        return "organizer/signin";
//    }
    
    @GetMapping("showNationRepresentative/{id}")
	public String showNationRepresentative(@PathVariable Long id, Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, 
						NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {
		if (id != null) {
			NationRepresentativeShowDto nationRepresentativeDtoShow = 
										nationRepresentativeService.nationRepresentativeEntityToNationRepresentativeDto(id);
			model.addAttribute("nationRepresentativeShow", nationRepresentativeDtoShow);
		} else {
			List<NationRepresentativeDto> allNationRepresentatives = 
											nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);

			model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationsList", nationService.findAll());
			model.addAttribute("nationRepresentatives", allNationRepresentatives);
			return "redirect:/organizer";
		}
		return "organizer/showNationRepresentative";
	}
    
    @GetMapping("prepareUpdateNationRepresentative/{id}")
	public String updateNationRepresentative(@PathVariable Long id,
			@Valid @ModelAttribute("deliveryUpdateModel") NationRepresentativeDto nationRepresentativeDto, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, 
			NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {

		if (id != null || !bindingResult.hasErrors()) {
			NationRepresentativeDto nationRepresentativeDtoUpdate = nationRepresentativeService.nationReprEntityToNationReprDto(id);
			model.addAttribute("nationsList", nationService.findAll());
			model.addAttribute("nationRepresentativeUpdateModel", nationRepresentativeDtoUpdate);
			return "organizer/updateNationRepresentative";
		} else {
			List<NationRepresentativeDto> allNationRepresentatives = 
					nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
			model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationsList", nationService.findAll());
			model.addAttribute("nationRepresentatives", allNationRepresentatives);
			redirectAttributes.addFlashAttribute("errorMessage", "Couldn't prepare update Nation Representative Page! Look out for Errors");
			return "redirect:/organizer";
		}
	}

	@PostMapping("executeUpdateNationRepresentative")
	public String executeUpdateNationRepresentative(@Valid @ModelAttribute("nationRepresentativeUpdateModel") 
			NationRepresentativeDto nationRepresentativeDto, BindingResult bindingResult, Model model, 
			NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto, 
			NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto, RedirectAttributes redirectAttributes) {
		
		nationRepresentativeUpdateValidator.validate(nationRepresentativeDto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("nationsList", nationService.findAll());
			return "organizer/updateNationRepresentative";
		} else {
			List<NationRepresentativeDto> allNationRepresentatives = 
					nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
			model.addAttribute("nationRepresentativeDeleteModel", new NationRepresentativeDeleteMessageDto());
			model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
			model.addAttribute("nationsList", nationService.findAll());
			model.addAttribute("nationRepresentatives", allNationRepresentatives);
			User user = userService.findById(Long.parseLong(nationRepresentativeDto.getUserDto().getId())).orElse(null);
			nationRepresentativeDto.setUserDto(userService.userDtoFromUserEntity(user));
			nationRepresentativeService.update(nationRepresentativeDto);
			redirectAttributes.addFlashAttribute("successMessage", "The Nation Representative has been successfully updated!");
			return "redirect:/organizer";
		}
	}
    
    @GetMapping("prepareDeleteNationRepresentative/{idNationRepresentativeDelete}")
    public String prepareDeleteNationRepresentative(@Valid @ModelAttribute("nationRepresentativeDeleteModel") 
					NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto, BindingResult bindingResult, 
					Model model, NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {
		
		if (nationRepresentativeDeleteMessageDto.getIdNationRepresentativeDelete() != null && 
				!nationRepresentativeDeleteMessageDto.getIdNationRepresentativeDelete().isEmpty()) {
				nationRepresentativeDeleteValidator.validate(nationRepresentativeDeleteMessageDto, bindingResult);
		
			if (bindingResult.hasErrors()) {
				List<NationRepresentativeDto> allNationRepresentatives = 
							nationRepresentativeService.findBySearchParameter(nationRepresentativeSearchFilterDto);
				
				model.addAttribute("nationRepresentativeDeleteModel", nationRepresentativeDeleteMessageDto);
	            model.addAttribute("searchFilters", nationRepresentativeSearchFilterDto);
	        	model.addAttribute("nationsList", nationService.findAll());
	        	model.addAttribute("nationRepresentatives", allNationRepresentatives);
	            return "redirect:/organizer";
	
			} else {
				model.addAttribute("nationRepresentativeDelete", nationRepresentativeDeleteMessageDto);
				return "organizer/deleteNationRepresentative";
			  }
		}	
			return "redirect:/organizer";
	}
    
    @GetMapping("executeDeleteNationRepresentative/{idNationRepresentativeDelete}")
	public String executeDeleteNationRepresentative(@Valid @ModelAttribute("nationRepresentativeDeleteModel") 
								NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto, RedirectAttributes redirectAttributes) {
		
		NationRepresentativeDto nationRepresentativeDtoDelete = 
				nationRepresentativeService.nationReprEntityToNationReprDto(
				Long.parseLong(nationRepresentativeDeleteMessageDto.getIdNationRepresentativeDelete()));
		
		nationRepresentativeService.delete(nationRepresentativeDtoDelete);
		redirectAttributes.addFlashAttribute("successMessage", "The Nation Representative has been successfully deleted!");
		return "redirect:/organizer";
	}
	
}
