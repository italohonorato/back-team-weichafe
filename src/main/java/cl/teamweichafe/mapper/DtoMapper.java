package cl.teamweichafe.mapper;

import java.util.List;

public interface DtoMapper<E, D> {

	D convertToDto(E entity);
	E convertToEntity(D dto);
	List<D> convertToDtoList(List<E> entities);
	List<E> convertToEntityList(List<D> dtos);
}
