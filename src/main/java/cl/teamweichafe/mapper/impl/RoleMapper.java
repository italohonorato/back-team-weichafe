package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.dto.RoleDto;
import cl.teamweichafe.mapper.DtoMapper;

@Component
public class RoleMapper implements DtoMapper<Role, RoleDto> {

	private ModelMapper mmodelMapper;
	
	public RoleMapper(ModelMapper mmodelMapper) {
		this.mmodelMapper = mmodelMapper;
	}

	@Override
	public RoleDto convertToDto(Role entity) {
		
		return this.mmodelMapper.map(entity, RoleDto.class);
	}

	@Override
	public Role convertToEntity(RoleDto dto) {
		
		return this.mmodelMapper.map(dto, Role.class);
	}

	@Override
	public List<RoleDto> convertToDtoList(List<Role> entities) {
		
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<Role> convertToEntityList(List<RoleDto> dtos) {
		
		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
