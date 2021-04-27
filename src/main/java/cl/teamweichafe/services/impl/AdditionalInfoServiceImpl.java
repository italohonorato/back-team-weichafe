package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.AdditionalInfo;
import cl.teamweichafe.repositories.AdditionalInfoRepository;
import cl.teamweichafe.services.AdditionalInfoService;

@Service
public class AdditionalInfoServiceImpl implements AdditionalInfoService {
	
	private AdditionalInfoRepository additionalInfoRepo;
	
	public AdditionalInfoServiceImpl(AdditionalInfoRepository additionalInfoRepo) {
		this.additionalInfoRepo = additionalInfoRepo;
	}

	@Override
	public AdditionalInfo findById(Integer id) throws Exception {
		
		Optional<AdditionalInfo> result = this.additionalInfoRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		return result.get();
	}

	@Override
	public List<AdditionalInfo> findall() {
		
		return this.additionalInfoRepo.findAll();
	}

	@Override
	public AdditionalInfo save(AdditionalInfo e) {
		
		return this.additionalInfoRepo.save(e);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		if (id == null || id <= 0) {
			throw new Exception("ID No válido");
		}
		
		Optional<AdditionalInfo> result = this.additionalInfoRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		this.additionalInfoRepo.deleteById(id);
		
		return true;
	}
	
	

}
