package it.solvingteam.course.springmvc.springmvcdemo.service;

import java.time.LocalDate;
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

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliveryDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliveryInsertMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliverySearchFilterDto;
import it.solvingteam.course.springmvc.springmvcdemo.mapper.DeliveryMapper;
import it.solvingteam.course.springmvc.springmvcdemo.model.Customer;
import it.solvingteam.course.springmvc.springmvcdemo.model.Delivery;
import it.solvingteam.course.springmvc.springmvcdemo.repository.DeliveryRepository;

@Service
public class DeliveryService {

	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<DeliveryDto> findAll() {
        List<Delivery> allDeliveries = this.deliveryRepository.findAll();
        return deliveryMapper.convertEntityToDto(allDeliveries);
    }

    public List<DeliveryDto> findBySearchParameter(DeliverySearchFilterDto deliverySearchFilterDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Delivery> cq = cb.createQuery(Delivery.class);

        Root<Delivery> delivery = cq.from(Delivery.class);
        List<Predicate> predicates = new ArrayList<>();

        if (deliverySearchFilterDto.getId() != null && !deliverySearchFilterDto.getId().equals("")) {
            predicates.add(cb.like(delivery.get("id").as(String.class), "%" + deliverySearchFilterDto.getId() + "%"));
        }

        if (deliverySearchFilterDto.getDescription() != null && !deliverySearchFilterDto.getDescription().equals("")) {
            predicates.add(cb.like(delivery.get("description"), "%" + deliverySearchFilterDto.getDescription() + "%"));
        }

        if (deliverySearchFilterDto.getPrice() != null && !deliverySearchFilterDto.getPrice().equals("")) {
            predicates.add(cb.greaterThanOrEqualTo(delivery.get("price"), deliverySearchFilterDto.getPrice()));
        }
        
        if (deliverySearchFilterDto.getPrice() != null && !deliverySearchFilterDto.getPrice().equals("")) {
            predicates.add(cb.lessThanOrEqualTo(delivery.get("price"), deliverySearchFilterDto.getPrice()));
        }

        if (deliverySearchFilterDto.getShippingDate() != null && !deliverySearchFilterDto.getShippingDate().equals("")) {
            predicates.add(cb.equal(delivery.get("shippingDate"), LocalDate.parse(deliverySearchFilterDto.getShippingDate())));
        }
        
        if (deliverySearchFilterDto.getCustomer() != null && !deliverySearchFilterDto.getCustomer().equals("")) {
        	Customer customer = new Customer();
            customer.setId(Integer.parseInt(deliverySearchFilterDto.getCustomer()));
        	predicates.add(cb.equal(delivery.get("customer"), customer));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return deliveryMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
    }

    public Delivery save(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.convertDtoToEntity(deliveryDto);
        return this.deliveryRepository.save(delivery);
    }

    public long count() {
        return this.deliveryRepository.count();
    }
    
    public Delivery insert(DeliveryInsertMessageDto insertDeliveryMessageDto) {
    	String description = insertDeliveryMessageDto.getDescription();
    	String shippingDate = insertDeliveryMessageDto.getShippingDate();
    	String price = insertDeliveryMessageDto.getPrice();
    	String idCustomerInsert = insertDeliveryMessageDto.getCustomer();
    	
    	Delivery delivery = new Delivery();
    	delivery.setDescription(description);
    	delivery.setShippingDate(LocalDate.parse(shippingDate));
    	delivery.setPrice(Double.parseDouble(price));
    	Optional<Customer> customer = customerService.findById(Integer.parseInt(idCustomerInsert));
    	delivery.setCustomer(customer.get()); 
    	return this.deliveryRepository.save(delivery);
    }

    public Optional<Delivery> findById(Integer id) {
    	return this.deliveryRepository.findById(id);
    }
    
    public DeliveryDto deliveryEntityToDeliveryDto(Integer id) {
    	Delivery delivery = this.findById(id).orElse(null);
    	return deliveryMapper.convertEntityToDto(delivery);
    }
    
    public Delivery deliveryDtoToDeliveryEntity(DeliveryDto deliveryDto) {
    	return deliveryMapper.convertDtoToEntity(deliveryDto);
    }

	public void update(DeliveryDto deliveryDto) {
		 Delivery delivery = deliveryMapper.convertDtoToEntity(deliveryDto);
		 this.deliveryRepository.save(delivery);
	}
	
	public void delete(Integer id) {
		Delivery delivery = this.findById(id).orElse(null);
		 this.deliveryRepository.delete(delivery);
	}
}
