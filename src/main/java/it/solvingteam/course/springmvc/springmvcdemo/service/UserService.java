package it.solvingteam.course.springmvc.springmvcdemo.service;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.UserSignupMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.exceptions.RoleNotFoundException;
import it.solvingteam.course.springmvc.springmvcdemo.model.Role;
import it.solvingteam.course.springmvc.springmvcdemo.model.User;
import it.solvingteam.course.springmvc.springmvcdemo.model.UserPrincipal;
import it.solvingteam.course.springmvc.springmvcdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }

    public Optional<User> findUserByUSername(String username) {
        return userRepository.findByUsername(username);
    }

    public void signup(UserSignupMessageDto userSignupMessageDto) throws RoleNotFoundException {
        String username = userSignupMessageDto.getUsername();
        String passwordEncoded = passwordEncoder.encode(userSignupMessageDto.getPassword());
        Role guestRole = this.roleService.loadGuestRole();

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoded);
        user.setRoles(Collections.singletonList(guestRole));

        this.userRepository.save(user);
    }


}
