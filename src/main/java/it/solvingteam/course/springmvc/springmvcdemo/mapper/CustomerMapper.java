package it.solvingteam.course.springmvc.springmvcdemo.mapper;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerDto;
import it.solvingteam.course.springmvc.springmvcdemo.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends AbstractMapper<Customer, CustomerDto> {
    @Override
    public CustomerDto convertEntityToDto(Customer entity) {
        if (entity == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(String.valueOf(entity.getId()));
        customerDto.setName(entity.getName());
        customerDto.setAddress(entity.getAddress());
        customerDto.setMobile(entity.getMobile());

        return customerDto;
    }

    @Override
    public Customer convertDtoToEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }

        Customer customer = new Customer();

        if (dto.getId() != null) {
            customer.setId(Integer.valueOf(dto.getId()));
        }

        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setMobile(dto.getMobile());

        return customer;
    }
}
