package cl.teamweichafe.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.UserDto;
import cl.teamweichafe.mapper.FunctionalMapper;
import cl.teamweichafe.repositories.UserRepository;
import cl.teamweichafe.services.ICrud;
import cl.teamweichafe.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User findById(Integer id) throws Exception {
		
		Optional<User> result = this.userRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		return result.get();
	}

	@Override
	public List<User> findall() {
		
		return this.userRepo.findAll();
	}

	@Override
	public User save(User e) {
		
		return this.userRepo.save(e);
	}

	@Override
	public boolean deleteById(Integer id) throws Exception {
		
		if (id == null || id <= 0) {
			throw new Exception("ID No válido");
		}
		
		Optional<User> result = this.userRepo.findById(id);
		
		if (!result.isPresent()) {
			throw new Exception(String.format("ID %s No encontrado", id));
		}
		
		this.userRepo.deleteById(id);
		
		return true;
	}

}
