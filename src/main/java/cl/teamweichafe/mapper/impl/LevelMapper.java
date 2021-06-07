package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cl.teamweichafe.domain.Level;
import cl.teamweichafe.dto.LevelDto;
import cl.teamweichafe.mapper.DtoMapper;

@Component
public class LevelMapper implements DtoMapper<Level, LevelDto> {

	private ModelMapper modelMapper;

	public LevelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public LevelDto convertToDto(Level entity) {

		return this.modelMapper.map(entity, LevelDto.class);
	}

	@Override
	public Level convertToEntity(LevelDto dto) {

		return this.modelMapper.map(dto, Level.class);
	}

	@Override
	public List<LevelDto> convertToDtoList(List<Level> entities) {

		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<Level> convertToEntityList(List<LevelDto> entities) {

		return entities.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
