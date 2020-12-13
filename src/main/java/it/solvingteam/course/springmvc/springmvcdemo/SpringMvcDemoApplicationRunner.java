package it.solvingteam.course.springmvc.springmvcdemo;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerDto;
import it.solvingteam.course.springmvc.springmvcdemo.model.Role;
import it.solvingteam.course.springmvc.springmvcdemo.service.CustomerService;
import it.solvingteam.course.springmvc.springmvcdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringMvcDemoApplicationRunner implements ApplicationRunner {


    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleService.count() == 0) {
            Role role = new Role();
            role.setName("GUEST");

            roleService.save(role);
        }

        if (customerService.count() == 0) {
            CustomerDto customerDto1 = new CustomerDto();
            customerDto1.setName("Customer 1");
            customerDto1.setAddress("Via Roma 2");
            customerDto1.setMobile("065025111");

            CustomerDto customerDto2 = new CustomerDto();
            customerDto2.setName("Customer 2");
            customerDto2.setAddress("Via Venezia 2");
            customerDto2.setMobile("065025222");

            customerService.save(customerDto1);
            customerService.save(customerDto2);
        }

    }

}
