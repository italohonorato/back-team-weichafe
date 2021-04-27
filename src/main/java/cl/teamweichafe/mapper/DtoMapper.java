package cl.teamweichafe.mapper;

import java.util.List;

public interface DtoMapper<E, D> {

	D convertToDto(E entity);
	List<D> convertToDtoList(List<E> entities);
	E convertToEntity(D dto);
}
