package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Level;
import cl.teamweichafe.repositories.LevelRepository;
import cl.teamweichafe.services.ICrud;
import cl.teamweichafe.services.LevelService;

@Service
public class LevelServiceImpl implements LevelService {
	
	private LevelRepository levelRepo;

	public LevelServiceImpl(LevelRepository levelRepo) {
		this.levelRepo = levelRepo;
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
