package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cl.teamweichafe.domain.AdditionalInfo;
import cl.teamweichafe.dto.AdditionalInfoDto;
import cl.teamweichafe.mapper.DtoMapper;

@Component
public class AdditionalInfoMapper implements DtoMapper<AdditionalInfo, AdditionalInfoDto> {

	private ModelMapper modelMapper;
	
	public AdditionalInfoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public AdditionalInfoDto convertToDto(AdditionalInfo entity) {
		
		return this.modelMapper.map(entity, AdditionalInfoDto.class);
	}

	@Override
	public AdditionalInfo convertToEntity(AdditionalInfoDto dto) {
		
		return this.modelMapper.map(dto, AdditionalInfo.class);
	}

	@Override
	public List<AdditionalInfoDto> convertToDtoList(List<AdditionalInfo> entities) {
		
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<AdditionalInfo> convertToEntityList(List<AdditionalInfoDto> dtos) {
		
		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

}
