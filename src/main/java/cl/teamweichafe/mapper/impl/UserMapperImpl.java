package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.UserDto;
import cl.teamweichafe.mapper.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto convertToDto(User entity) {
		
		UserDto userDto = this.modelMapper.map(entity, UserDto.class);
		return userDto;
	}

	@Override
	public User convertToEntity(UserDto dto) {
		
		User user = this.modelMapper.map(dto, User.class);
		return user;
	}

	@Override
	public List<UserDto> convertToDtoList(List<User> entities) {
		
		List<UserDto> dtolist = entities.stream().map(this::convertToDto).collect(Collectors.toList());
		return dtolist;
	}

}
