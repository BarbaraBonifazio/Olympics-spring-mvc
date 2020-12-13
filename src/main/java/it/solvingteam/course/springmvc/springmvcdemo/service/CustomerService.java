package it.solvingteam.course.springmvc.springmvcdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerDeleteMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomerInsertMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.CustomersSearchFilterDto;
import it.solvingteam.course.springmvc.springmvcdemo.mapper.CustomerMapper;
import it.solvingteam.course.springmvc.springmvcdemo.model.Customer;
import it.solvingteam.course.springmvc.springmvcdemo.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private EntityManager entityManager;

    public List<CustomerDto> findAll() {
        List<Customer> allCustomers = this.customerRepository.findAll();
        return customerMapper.convertEntityToDto(allCustomers);
    }

    public List<CustomerDto> findBySearchParameter(CustomersSearchFilterDto customersSearchFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

        Root<Customer> customer = cq.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();

        if (customersSearchFilterDto.getId() != null && !customersSearchFilterDto.getId().equals("")) {
            predicates.add(cb.like(customer.get("id").as(String.class), "%" + customersSearchFilterDto.getId() + "%"));
        }

        if (customersSearchFilterDto.getName() != null && !customersSearchFilterDto.getName().equals("")) {
            predicates.add(cb.like(customer.get("name"), "%" + customersSearchFilterDto.getName() + "%"));
        }

        if (customersSearchFilterDto.getAddress() != null && !customersSearchFilterDto.getAddress().equals("")) {
            predicates.add(cb.like(customer.get("address"), "%" + customersSearchFilterDto.getAddress() + "%"));
        }

        if (customersSearchFilterDto.getMobile() != null && !customersSearchFilterDto.getMobile().equals("")) {
            predicates.add(cb.like(customer.get("mobile"), "%" + customersSearchFilterDto.getMobile() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return customerMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
    }

    public Customer save(CustomerDto customerDto) {
        Customer customer = customerMapper.convertDtoToEntity(customerDto);
        return this.customerRepository.save(customer);
    }

    public long count() {
        return this.customerRepository.count();
    }
    
    public Customer insert(CustomerInsertMessageDto insertCustomerMessageDto) {
    	String name = insertCustomerMessageDto.getName();
    	String mobile = insertCustomerMessageDto.getMobile();
    	String address = insertCustomerMessageDto.getAddress();
    	
    	Customer customer = new Customer();
    	customer.setName(name);
    	customer.setMobile(mobile);
    	customer.setAddress(address);
    	return this.customerRepository.save(customer);
    }

    public Optional<Customer> findById(Integer id) {
    	return this.customerRepository.findById(id);
    }
    
    public CustomerDto customerEntityToCustomerDto(Integer id) {
    	Customer customer = this.findById(id).orElse(null);
    	return customerMapper.convertEntityToDto(customer);
    }
    
    public Customer customerDtoToCustomerEntity(CustomerDto customerDto) {
    	return customerMapper.convertDtoToEntity(customerDto);
    }

	public void update(CustomerDto customerDto) {
		 Customer customer = customerMapper.convertDtoToEntity(customerDto);
		 this.customerRepository.save(customer);
	}
	
	public void delete(CustomerDto customerDto) {
		Customer customer = customerMapper.convertDtoToEntity(customerDto);
		this.customerRepository.delete(customer);
	}
    
	public Customer getCustomerEntityFromCustomerDeleteMessageDto(CustomerDeleteMessageDto customerDeleteMessageDto) {
		Customer customer = this.findById(Integer.parseInt(customerDeleteMessageDto.getIdCustomerDelete())).orElse(null);
		return customer;
	}
	
}
