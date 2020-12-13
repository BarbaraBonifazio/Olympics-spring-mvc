package it.solvingteam.course.springmvc.springmvcdemo.service;

import it.solvingteam.course.springmvc.springmvcdemo.exceptions.RoleNotFoundException;
import it.solvingteam.course.springmvc.springmvcdemo.model.Role;
import it.solvingteam.course.springmvc.springmvcdemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static it.solvingteam.course.springmvc.springmvcdemo.utils.Constants.GUEST_ROLE;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) throws RoleNotFoundException {
        return this.roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    public Role loadGuestRole() throws RoleNotFoundException {
        return this.findByName(GUEST_ROLE);
    }

    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    public long count() {
        return this.roleRepository.count();
    }

}
