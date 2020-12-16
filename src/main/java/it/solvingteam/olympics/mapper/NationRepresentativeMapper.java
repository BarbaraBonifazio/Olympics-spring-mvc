package it.solvingteam.olympics.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.olympics.dto.NationRepresentativeDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeInsertMessageDto;
import it.solvingteam.olympics.dto.messages.NationRepresentativeShowDto;
import it.solvingteam.olympics.model.nation.Nation;
import it.solvingteam.olympics.model.nationRepresentative.NationRepresentative;
import it.solvingteam.olympics.model.user.User;

@Component
public class NationRepresentativeMapper extends AbstractMapper<NationRepresentative, NationRepresentativeDto> {

	@Autowired
	NationMapper nationMapper;
	@Autowired 
	UserMapper userMapper;
	@Autowired 
	AthleteMapper athleteMapper;
	
	@Override
	public NationRepresentativeDto convertEntityToDto(NationRepresentative entity) {
		 if (entity == null) {
	            return null;
	        }
		 
		 NationRepresentativeDto NationRepresentativeDto = new NationRepresentativeDto();
			NationRepresentativeDto.setId(String.valueOf(entity.getId()));
			NationRepresentativeDto.setName(entity.getName());
			NationRepresentativeDto.setSurname(entity.getSurname());
			NationRepresentativeDto.setFiscalCode(entity.getFiscalCode());
			NationRepresentativeDto.setNationDto(nationMapper.convertEntityToDto(entity.getNation()));
			NationRepresentativeDto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
			return NationRepresentativeDto;
	}
	
	public NationRepresentativeShowDto convertEntityToNationRepresentativeShow(NationRepresentative entity) {
		 if (entity == null) {
	            return null;
	        }
		 
		 NationRepresentativeShowDto nationRepresentativeShotDto = new NationRepresentativeShowDto();
		 nationRepresentativeShotDto.setId(String.valueOf(entity.getId()));
		 nationRepresentativeShotDto.setName(entity.getName());
		 nationRepresentativeShotDto.setSurname(entity.getSurname());
		 nationRepresentativeShotDto.setFiscalCode(entity.getFiscalCode());
		 nationRepresentativeShotDto.setNationDto(nationMapper.convertEntityToDto(entity.getNation()));
		 nationRepresentativeShotDto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		 nationRepresentativeShotDto.setAthleteDtoList(athleteMapper.convertEntityToListDto(entity.getAthletes()));
			return nationRepresentativeShotDto;
	}

	@Override
	public NationRepresentative convertDtoToEntity(NationRepresentativeDto dto) {
		if (dto == null) {
            return null;
        }
		
        NationRepresentative nationRepresentative = new NationRepresentative();

        if (dto.getId() != null) {
        	nationRepresentative.setId(Long.valueOf(dto.getId()));
        }

        nationRepresentative.setName(dto.getName());
        nationRepresentative.setSurname(dto.getSurname());
        nationRepresentative.setFiscalCode(dto.getFiscalCode());
        
        return nationRepresentative;
	}
	
	public NationRepresentative convertDtoToEntity(NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto) {
		if (nationRepresentativeInsertMessageDto == null) {
            return null;
        }
		
        NationRepresentative nationRepresentative = new NationRepresentative();

        nationRepresentative.setName(nationRepresentativeInsertMessageDto.getName());
        nationRepresentative.setSurname(nationRepresentativeInsertMessageDto.getSurname());
        nationRepresentative.setFiscalCode(nationRepresentativeInsertMessageDto.getFiscalCode());
        Nation nation = new Nation();
        nation.setId(Long.parseLong(nationRepresentativeInsertMessageDto.getNation()));
        nationRepresentative.setNation(nation);
        User user = new User();
        user.setName(nationRepresentativeInsertMessageDto.getName());
        user.setSurname(nationRepresentativeInsertMessageDto.getSurname());
        user.setFiscalCode(nationRepresentativeInsertMessageDto.getFiscalCode());
        user.setUsername(nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getUsername());
        user.setPassword(nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getPassword());
        
        nationRepresentative.setUser(user);
        
        return nationRepresentative;
	}
}
