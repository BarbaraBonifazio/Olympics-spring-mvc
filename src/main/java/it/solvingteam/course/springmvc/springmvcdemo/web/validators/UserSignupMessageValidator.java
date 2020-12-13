package it.solvingteam.course.springmvc.springmvcdemo.web.validators;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.UserSignupMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.model.User;
import it.solvingteam.course.springmvc.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserSignupMessageValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSignupMessageDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserSignupMessageDto userSignupMessageDto = (UserSignupMessageDto) o;

        if (!userSignupMessageDto.getPassword().equals(userSignupMessageDto.getRepeatePassword())) {
            errors.rejectValue("repeatePassword", "passwordsDoesntMatch", "Password doesn't match");
        }

        Optional<User> user = userService.findUserByUSername(userSignupMessageDto.getUsername());
        if (user.isPresent()) {
            errors.rejectValue("username", "usernameAlreadyExists", "Username already exists");
        }
    }
}
