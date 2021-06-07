package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Level;
import cl.teamweichafe.dto.LevelDto;
import cl.teamweichafe.mapper.FunctionalMapper;
import cl.teamweichafe.repositories.LevelRepository;
import cl.teamweichafe.services.LevelService;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {
	
	private LevelRepository levelRepo;
	private ModelMapper modelMapper;

	public LevelServiceImpl(LevelRepository levelRepo, ModelMapper modelMapper) {
		this.levelRepo = levelRepo;
		this.modelMapper = modelMapper;
	}
	
	public LevelDto mapToDto(Level entity) {
		
		LevelDto dto = this.modelMapper.map(entity, LevelDto.class);
		
		return dto;
	}
	
	public Level mapToEntity(LevelDto dto) {
		
		Level level = this.modelMapper.map(dto, Level.class);
		
		return level;
	}

	@Override
	public Level findById(Integer id) throws Exception {
		
		Optional<Level> result = this.levelRepo.findById(id);
		
		if(!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		return result.get();
	}

	@Override
	public List<Level> findall() {
		
		return this.levelRepo.findAll();
	}

	@Override
	public Level save(Level e) {

		return this.levelRepo.save(e);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		
		if (id == null || id <= 0) {
			throw new Exception("ID No válido");
		}
		
		Optional<Level> result = this.levelRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		this.levelRepo.deleteById(id);
		
		return true;
	}

}
