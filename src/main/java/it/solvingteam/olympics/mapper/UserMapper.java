package it.solvingteam.olympics.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.olympics.dto.UserDto;
import it.solvingteam.olympics.dto.messages.UserSearchDto;
import it.solvingteam.olympics.model.user.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDto>{

	@Override
	public UserDto convertEntityToDto(User entity) {
		if (entity == null) {
            return null;
        }
		
		UserDto userDto = new UserDto();
		userDto.setId(String.valueOf(entity.getId()));
		userDto.setName(entity.getName());
		userDto.setSurname(entity.getSurname());
		userDto.setFiscalCode(entity.getFiscalCode());
		userDto.setUsername(entity.getUsername());
		userDto.setPassword(entity.getPassword());
		
		return userDto;
	}
	
	public UserSearchDto convertEntityToUserSearchDto(User entity) {
		if (entity == null) {
            return null;
        }
		
		UserSearchDto userSearchDto = new UserSearchDto();
		userSearchDto.setId(String.valueOf(entity.getId()));
		userSearchDto.setUsername(entity.getUsername());
		
		return userSearchDto;
	}

	@Override
	public User convertDtoToEntity(UserDto dto) {
		if (dto == null) {
            return null;
        }
		
		User user = new User();
		user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setFiscalCode(dto.getFiscalCode());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
		return user;
	}


		
}
