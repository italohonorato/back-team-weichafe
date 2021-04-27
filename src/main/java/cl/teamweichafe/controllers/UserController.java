package cl.teamweichafe.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<User> users = this.userService.findall();
		List<UserDto> dtoList = this.userMapper.convertToDtoList(users);
		
		return new ResponseEntity<List<UserDto>>(dtoList, HttpStatus.OK);
	}
	
//	@Secured("ROLE_ADMIN")
	@RolesAllowed({"ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Integer id) throws Exception{
		
		User user = this.userService.findById(id);
		UserDto userDto = this.userMapper.convertToDto(user);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

}
