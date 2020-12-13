package it.solvingteam.course.springmvc.springmvcdemo.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliveryDto;
import it.solvingteam.course.springmvc.springmvcdemo.model.Delivery;

@Component
public class DeliveryMapper extends AbstractMapper <Delivery, DeliveryDto>{
	
	@Autowired 
	CustomerMapper customerMapper;
	
	@Override
    public DeliveryDto convertEntityToDto(Delivery entity) {
        if (entity == null) {
            return null;
        }

        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(String.valueOf(entity.getId()));
        deliveryDto.setDescription(entity.getDescription());
        deliveryDto.setShippingDate(String.valueOf(entity.getShippingDate()));
        deliveryDto.setPrice(String.valueOf(entity.getPrice()));
        deliveryDto.setCustomerDto(customerMapper.convertEntityToDto(entity.getCustomer()));

        return deliveryDto;
    }

    @Override
    public Delivery convertDtoToEntity(DeliveryDto dto) {
        if (dto == null) {
            return null;
        }

        Delivery delivery = new Delivery();

        if (dto.getId() != null) {
            delivery.setId(Integer.valueOf(dto.getId()));
        }

        delivery.setDescription(dto.getDescription());
        delivery.setShippingDate(LocalDate.parse(dto.getShippingDate()));
        delivery.setPrice(Double.parseDouble(dto.getPrice()));
        delivery.setCustomer(customerMapper.convertDtoToEntity(dto.getCustomerDto()));
        return delivery;
    }
	
	
}
