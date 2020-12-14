package it.solvingteam.olympics.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.solvingteam.olympics.dto.messages.UserSigninMessageDto;
import it.solvingteam.olympics.dto.messages.UserSignupMessageDto;
import it.solvingteam.olympics.service.UserService;
import it.solvingteam.olympics.web.validators.UserSignupMessageValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSignupMessageValidator userSignupMessageValidator;

    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("userSignupModel", new UserSignupMessageDto());
        return "auth/signup";
    }

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute("userSignupModel") UserSignupMessageDto userSignupMessageDto, BindingResult bindingResult) {
        userSignupMessageValidator.validate(userSignupMessageDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        } else {
            userService.signup(userSignupMessageDto);
            return "redirect:/auth/signin";
        }
    }

    @GetMapping("signin")
    public String signin(Model model) {
        model.addAttribute("userSigninModel", new UserSigninMessageDto());
        return "auth/signin";
    }

}
