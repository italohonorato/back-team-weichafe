package cl.teamweichafe.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.teamweichafe.mapper.FunctionalMapper;

@Component
public class MapperUtil<F, T> {

	@Autowired
	private ModelMapper modelMapper;
	
	public T mapFromTo(F from, Class<T> to) {
		
		FunctionalMapper<F, T> mapper = (f, t) -> this.modelMapper.map(f, t);
		
		return mapper.mapFromTo(from, to);
	}
	
	public List<T> mapToList(List<F> listFrom, Class<T> to){
		
		List<T> listMap = listFrom.stream().map(item -> mapFromTo(item, to)).collect(Collectors.toList());
		
		return listMap;
	}
}
