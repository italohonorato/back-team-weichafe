package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Measure;
import cl.teamweichafe.repositories.MeasureRepository;
import cl.teamweichafe.services.MeasureService;

@Service
@Transactional
public class MeasureServiceImpl implements MeasureService {
	
	@Autowired
	private MeasureRepository measureRepo;

	@Override
	public Measure findById(Integer id) throws Exception {
		
		Optional<Measure> measure = this.measureRepo.findById(id);
		
		if (!measure.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		return measure.get();
	}

	@Override
	public List<Measure> findall() {
		
		return this.measureRepo.findAll();
	}

	@Override
	public Measure save(Measure e) {
		
		return this.measureRepo.save(e);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		
		this.measureRepo.deleteById(id);
		
		return true;
	}

}
