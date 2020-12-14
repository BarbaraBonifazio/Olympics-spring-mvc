package it.solvingteam.olympics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.solvingteam.olympics.dto.messages.UserSignupMessageDto;
import it.solvingteam.olympics.model.user.User;
import it.solvingteam.olympics.model.user.UserPrincipal;
import it.solvingteam.olympics.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
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
    
    public void signup(UserSignupMessageDto userSignupMessageDto) {
        String username = userSignupMessageDto.getUsername();
        String passwordEncoded = passwordEncoder.encode(userSignupMessageDto.getPassword());

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoded);

        this.userRepository.save(user);
    }
}
