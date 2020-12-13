package it.solvingteam.course.springmvc.springmvcdemo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliveryDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliveryInsertMessageDto;
import it.solvingteam.course.springmvc.springmvcdemo.dto.messages.DeliverySearchFilterDto;
import it.solvingteam.course.springmvc.springmvcdemo.service.CustomerService;
import it.solvingteam.course.springmvc.springmvcdemo.service.DeliveryService;

@Controller
@RequestMapping("delivery")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	public String list(DeliverySearchFilterDto deliveriesSearchFilterDto, Model model) {
		List<DeliveryDto> allDeliveries = deliveryService.findBySearchParameter(deliveriesSearchFilterDto);

		model.addAttribute("searchFilters", deliveriesSearchFilterDto);
		model.addAttribute("deliveries", allDeliveries);
		model.addAttribute("customersDelivery", customerService.findAll());
		return "delivery/list";
	}

	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("deliveryInsertModel", new DeliveryInsertMessageDto());
		model.addAttribute("customersInsertDelivery", customerService.findAll());
		return "delivery/insert";
	}

	@PostMapping("executeInsert")
	public String executeInsert( @Valid @ModelAttribute("deliveryInsertModel") DeliveryInsertMessageDto deliveryInsertMessageDto,
								BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("customersInsertDelivery", customerService.findAll());
			return "delivery/insert";
		} else {
			deliveryService.insert(deliveryInsertMessageDto);
			return "redirect:/delivery";
		}
	}

	@GetMapping("show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		if (id != null) {
			DeliveryDto deliveryDtoShow = deliveryService.deliveryEntityToDeliveryDto(id);
			model.addAttribute("deliveryShow", deliveryDtoShow);
		} else {
			return "redirect:/delivery";
		}
		return "delivery/show";
	}

	@GetMapping("update/{id}")
	public String update(@PathVariable Integer id,
			@Valid @ModelAttribute("deliveryUpdateModel") DeliveryDto deliveryDto, BindingResult bindingResult,
			Model model) {

		if (id != null || !bindingResult.hasErrors()) {
			DeliveryDto deliveryDtoUpdate = deliveryService.deliveryEntityToDeliveryDto(id);
			model.addAttribute("customersUpdateDelivery", customerService.findAll());
			model.addAttribute("deliveryUpdateModel", deliveryDtoUpdate);
			return "delivery/update";
		} else {
			return "redirect:/delivery";
		}
	}

	@PostMapping("executeUpdate")
	public String executeUpdate(@Valid @ModelAttribute("deliveryUpdateModel") DeliveryDto deliveryDto,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("customersUpdateDelivery", customerService.findAll());
			return "delivery/update";
		} else {
			deliveryService.update(deliveryDto);
			return "redirect:/delivery";
		}
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {

		if (id != null) {
			model.addAttribute("idDeliveryDelete", id);
			return "delivery/delete";
		} else {
			return "redirect:/delivery";
		}
	}

	@GetMapping("executeDelete")
	public String executeDelete(@RequestParam("idDeliveryDelete") Integer id) {
		deliveryService.delete(id);
		return "redirect:/delivery";
	}
}
