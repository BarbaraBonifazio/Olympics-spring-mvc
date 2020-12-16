package it.solvingteam.olympics.service;

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

import it.solvingteam.olympics.dto.NationRepresentativeDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeDeleteMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeSearchFilterDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.olympics.mapper.NationMapper;
import it.solvingteam.olympics.mapper.NationRepresentativeMapper;
import it.solvingteam.olympics.model.nation.Nation;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.model.user.User;
import it.solvingteam.olympics.repository.NationRepresentativeRepository;

@Service
public class NationRepresentativeService {

	@Autowired
	NationRepresentativeRepository nationRepresentativeRepository;
	
	@Autowired
	NationService nationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NationRepresentativeMapper nationRepresentativeMapper;
	
	@Autowired
	NationMapper nationMapper;
	
	
	@Autowired
    private EntityManager entityManager;
	
	 public List<NationRepresentative> findAll() {
	        List<NationRepresentative> allNationRepresentatives = this.nationRepresentativeRepository.findAll();
	        return allNationRepresentatives;
	    }
	
	//metodo che utilizzerà l'organizzatore per creare il rappresentante nazione
	//richiamo lo user da db tramite username in quanto attributo del nationRepresentativeDto (che non ha ancora un id)
	public void insert(NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto) {
		NationRepresentative nationRepresentative = nationRepresentativeMapper.convertNationRepresentativeInsertDtoToEntity(nationRepresentativeInsertMessageDto);
		Nation nation = this.nationService.findById(nationRepresentative.getNation().getId()).orElse(null);
		nationRepresentative.setNation(nation);
		User user = this.userService.findUserByUSername(nationRepresentative.getUser().getUsername()).orElse(null);
		nationRepresentative.setUser(user);
    	this.nationRepresentativeRepository.save(nationRepresentative);
    }

	 public Optional<NationRepresentative> findById(Long id) {
	    	return this.nationRepresentativeRepository.findById(id);
	 }
	 
	 public Optional<NationRepresentative> findByFiscalCode(String fiscalCode) {
	        return this.nationRepresentativeRepository.findByFiscalCode(fiscalCode);
	    }

	public List<NationRepresentativeDto> findBySearchParameter(
			NationRepresentativeSearchFilterDto nationRepresentativeSearchFilterDto) {
		 	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<NationRepresentative> cq = cb.createQuery(NationRepresentative.class);

	        Root<NationRepresentative> nationRepresentative = cq.from(NationRepresentative.class);
	        List<Predicate> predicates = new ArrayList<>();

	        if (nationRepresentativeSearchFilterDto.getName() != null && !nationRepresentativeSearchFilterDto.getName().equals("")) {
	            predicates.add(cb.like(nationRepresentative.get("name"), "%" + nationRepresentativeSearchFilterDto.getName() + "%"));
	        }

	        if (nationRepresentativeSearchFilterDto.getSurname() != null && !nationRepresentativeSearchFilterDto.getSurname().equals("")) {
	            predicates.add(cb.like(nationRepresentative.get("surname"), "%" + nationRepresentativeSearchFilterDto.getSurname() + "%"));
	        }

	        if (nationRepresentativeSearchFilterDto.getFiscalCode() != null && !nationRepresentativeSearchFilterDto.getFiscalCode().equals("")) {
	            predicates.add(cb.like(nationRepresentative.get("fiscalCode"), "%" + nationRepresentativeSearchFilterDto.getFiscalCode() + "%"));
	        }
	        
	        if (nationRepresentativeSearchFilterDto.getUserSearchDto() != null && !nationRepresentativeSearchFilterDto.getUserSearchDto().getUsername().equals("")) {
	        	User user = userService.findUserByUSername(nationRepresentativeSearchFilterDto.getUserSearchDto().getUsername()).orElse(null);
	        	predicates.add(cb.equal(nationRepresentative.get("user"), user));
	        }
	        
	        if (nationRepresentativeSearchFilterDto.getNation() != null && 
        			!nationRepresentativeSearchFilterDto.getNation().equals("")) {
        	
	        	Nation nation = new Nation();
	        	nation.setId(Long.parseLong(nationRepresentativeSearchFilterDto.getNation()));
	        	predicates.add(cb.equal(nationRepresentative.get("nation"), nation));
	        }

	        cq.where(predicates.toArray(new Predicate[0]));
	        return nationRepresentativeMapper.convertEntityToDto(entityManager.createQuery(cq).getResultList());
	}
	
	public NationRepresentativeShowDto NationRepresentativeEntityToNationRepresentativeDto(Long id) {
		NationRepresentative nationRepresentative = this.findById(id).orElse(null);
    	return nationRepresentativeMapper.convertEntityToNationRepresentativeShow(nationRepresentative);
    }

	public NationRepresentative getNationReprEntityFromNationReprDeleteMessageDto(
			NationRepresentativeDeleteMessageDto nationRepresentativeDeleteMessageDto) {
		NationRepresentative nationRepresentative = this.findById(Long.parseLong(
				nationRepresentativeDeleteMessageDto.getIdNationRepresentativeDelete())).orElse(null);
		return nationRepresentative;
	}

	public NationRepresentativeDto nationReprEntityToNationReprDto(Long id) {
		NationRepresentative nationRepresentative = this.findById(id).orElse(null);
    	return nationRepresentativeMapper.convertEntityToDto(nationRepresentative);
	}

	public void delete(NationRepresentativeDto nationRepresentativeDtoDelete) {
		NationRepresentative nationRepresentative = this.findById(Long.parseLong(nationRepresentativeDtoDelete.getId())).orElse(null);
		this.nationRepresentativeRepository.delete(nationRepresentative);
	}
}
