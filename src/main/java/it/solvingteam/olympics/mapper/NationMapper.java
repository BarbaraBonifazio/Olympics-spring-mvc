package it.solvingteam.olympics.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.olympics.dto.NationDto;
import it.solvingteam.olympics.model.nation.Nation;

@Component
public class NationMapper extends AbstractMapper<Nation, NationDto>{

	@Override
	public NationDto convertEntityToDto(Nation entity) {
		 if (entity == null) {
	            return null;
	        }
		 
		 NationDto nationDto = new NationDto();
		 nationDto.setId(String.valueOf(entity.getId()));
		 nationDto.setName(entity.getName());
		 return nationDto;
	}

	@Override
	public Nation convertDtoToEntity(NationDto dto) {
		if (dto == null) {
            return null;
        }
		
        Nation nation = new Nation();

        if (dto.getId() != null) {
        	nation.setId(Long.valueOf(dto.getId()));
        }

        nation.setName(dto.getName());
        
        return nation;
	}

}
