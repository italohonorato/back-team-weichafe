package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.repositories.RoleRepository;
import cl.teamweichafe.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findById(Integer id) throws Exception {
		
		Optional<Role> result = this.roleRepo.findById(id);
		
		if(!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		return result.get();
	}

	@Override
	public List<Role> findall() {
		
		return this.roleRepo.findAll();
	}

	@Override
	public Role save(Role e) {
		
		return this.roleRepo.save(e);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		
		if (id == null || id <= 0) {
			throw new Exception("ID No válido");
		}
		
		Optional<Role> result = this.roleRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		this.roleRepo.deleteById(id);
		
		return true;
	}

}
