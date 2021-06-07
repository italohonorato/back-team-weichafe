package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Assistance;
import cl.teamweichafe.domain.AssistancePk;
import cl.teamweichafe.repositories.AssistanceRepository;
import cl.teamweichafe.services.AssistanceService;

@Service
@Transactional
public class AssistanceServiceImpl implements AssistanceService {

	private AssistanceRepository assistanceRepo;

	public AssistanceServiceImpl(AssistanceRepository assistanceRepo) {
		this.assistanceRepo = assistanceRepo;
	}

	@Override
	public Assistance findByPk(AssistancePk pk) throws Exception {
		
		Optional<Assistance> result = this.assistanceRepo.findById(pk);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("PK [%s,%s, %s] No encontrado", pk.getAssistanceId(), pk.getLevel().getLevelId(), pk.getAssistanceDate().toString()));
		}
		return null;
	}

	@Override
	public List<Assistance> findall() {
		
		return this.assistanceRepo.findAll();
	}

	@Override
	public Assistance save(Assistance e) {
		
		return this.assistanceRepo.save(e);
	}

	@Override
	public boolean deleteByPk(AssistancePk pk) throws Exception {
		
		if (pk == null) {
			throw new Exception("ID No válido");
		}
		
		Optional<Assistance> result = this.assistanceRepo.findById(pk);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("PK [%s,%s, %s] No encontrado", pk.getAssistanceId(), pk.getLevel().getLevelId(), pk.getAssistanceDate().toString()));
		}
		
		this.assistanceRepo.deleteById(pk);
		
		return true;
	}

}
