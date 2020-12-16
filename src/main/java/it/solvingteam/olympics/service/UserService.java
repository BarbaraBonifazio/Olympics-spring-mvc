package it.solvingteam.olympics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.model.Role;
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
    
    public void signupNationRepresentative(NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto) {
        String username = nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getUsername();
        String passwordEncoded = passwordEncoder.encode(nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getPassword());
        
        User user = new User();
        user.setName(nationRepresentativeInsertMessageDto.getName());
        user.setSurname(nationRepresentativeInsertMessageDto.getSurname());
        user.setFiscalCode(nationRepresentativeInsertMessageDto.getFiscalCode());
        user.setUsername(username);
        user.setPassword(passwordEncoded);
        user.setRole(Role.RAPPRESENTANTE_NAZIONE);

        this.userRepository.save(user);
    }
    
    public Optional<User> findById(Long id) {
    	return this.userRepository.findById(id);
    }
    
    
}
