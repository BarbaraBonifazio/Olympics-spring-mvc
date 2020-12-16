package it.solvingteam.olympics.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.olympics.dto.NationDto;
import it.solvingteam.olympics.mapper.NationMapper;
import it.solvingteam.olympics.model.nation.Nation;
import it.solvingteam.olympics.repository.NationRepository;

@Service
public class NationService {

	@Autowired
	NationRepository nationRepository;
	
	@Autowired
	NationMapper nationMapper;
	
	@Autowired
    private EntityManager entityManager;
	
	 public List<NationDto> findAll() {
	        List<Nation> allNations = this.nationRepository.findAll();
	        return nationMapper.convertEntityToDto(allNations);
	    }
	
	 public Optional<Nation> findById(Long id) {
	    	return this.nationRepository.findById(id);
	    }
}
