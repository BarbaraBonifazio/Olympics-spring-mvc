package it.solvingteam.olympics.mapper;

import org.springframework.stereotype.Component;
import it.solvingteam.olympics.dto.messages.AthleteDto;
import it.solvingteam.olympics.model.athlete.Athlete;


@Component
public class AthleteMapper extends AbstractMapper<Athlete, AthleteDto>{

	@Override
	public AthleteDto convertEntityToDto(Athlete entity) {
		if (entity == null) {
            return null;
        }

		AthleteDto AthleteDto = new AthleteDto();
		AthleteDto.setId(String.valueOf(entity.getId()));
		AthleteDto.setName(entity.getName());
		AthleteDto.setSurname(entity.getSurname());
		AthleteDto.setFiscalCode(entity.getFiscalCode());
		AthleteDto.setTotalScore(String.valueOf(entity.getTotalScore()));
		AthleteDto.setGoldMedals(String.valueOf(entity.getGoldMedals()));
		AthleteDto.setSilverMedals(String.valueOf(entity.getSilverMedals()));
		AthleteDto.setBronzeMedals(String.valueOf(entity.getBronzeMedals()));
		
        return AthleteDto;
    }

	@Override
	public Athlete convertDtoToEntity(AthleteDto dto) {
		if (dto == null) {
            return null;
        }

        Athlete athlete = new Athlete();

        if (dto.getId() != null) {
        	athlete.setId(Long.valueOf(dto.getId()));
        }

        athlete.setName(dto.getName());
        athlete.setSurname(dto.getSurname());
        athlete.setFiscalCode(dto.getFiscalCode());
        athlete.setTotalScore(Integer.parseInt(dto.getTotalScore()));
        athlete.setGoldMedals(Integer.parseInt(dto.getGoldMedals()));
        athlete.setSilverMedals(Integer.parseInt(dto.getSilverMedals()));
        athlete.setBronzeMedals(Integer.parseInt(dto.getBronzeMedals()));
        
        return athlete;
	}
	
	

}
