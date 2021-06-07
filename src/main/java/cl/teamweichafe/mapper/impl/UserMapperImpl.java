package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.UserDto;
import cl.teamweichafe.mapper.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {

	private ModelMapper modelMapper;

	public UserMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto convertToDto(User entity) {

		return this.modelMapper.map(entity, UserDto.class);
	}

	@Override
	public User convertToEntity(UserDto dto) {

		return this.modelMapper.map(dto, User.class);
	}

	@Override
	public List<UserDto> convertToDtoList(List<User> entities) {

		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<User> convertToEntityList(List<UserDto> dtos) {

		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
