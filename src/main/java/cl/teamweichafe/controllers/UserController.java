package cl.teamweichafe.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.UserDto;
import cl.teamweichafe.mapper.UserMapper;
import cl.teamweichafe.services.impl.UserServiceImpl;
/**
 * 
 * @author italohonorato
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ModelMapper mapper;
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<User> users = this.userService.findall();
		List<UserDto> dtoList = this.userMapper.convertToDtoList(users);
		
		return new ResponseEntity<List<UserDto>>(dtoList, HttpStatus.OK);
	}
	
	@RolesAllowed({"ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Integer id) throws Exception{
		User user = this.userService.findById(id);
		UserDto userDto = this.userMapper.convertToDto(user);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PostMapping({"ROLE_ADMIN"})
	public ResponseEntity<?> create(@RequestBody UserDto dto){
		Optional<User> user = Optional.of(this.userMapper.convertToEntity(dto));
		
		if (!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		user.ifPresent(u -> this.userService.save(u));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception{
		this.userService.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
