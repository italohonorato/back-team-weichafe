package cl.teamweichafe.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.RoleDto;
import cl.teamweichafe.dto.UserDto;
import cl.teamweichafe.mapper.UserMapper;
import cl.teamweichafe.services.RoleService;
import cl.teamweichafe.services.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
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
	private RoleService roleService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
//	@Transient
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@ApiOperation(value = "Endpoint to retrieve all existing Users")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
	@GetMapping
	public ResponseEntity<CollectionModel<UserDto>> getAllUsers(){
		List<User> users = this.userService.findall();
		List<UserDto> dtoList = this.userMapper.convertToDtoList(users);
		dtoList.forEach(dto -> {
			try {
				Link userLink = linkTo(methodOn(UserController.class).getById(dto.getUserId())).withSelfRel();
				dto.add(userLink);
				dto.getRoles().forEach(dtoRole -> {
					try {
						Link roleLink = linkTo(methodOn(RoleController.class).getById(dtoRole.getRoleId())).withRel("roles");
						dtoRole.add(roleLink);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Link selfLink = linkTo(UserController.class).withSelfRel();
		CollectionModel<UserDto> result = CollectionModel.of(dtoList, selfLink);
		
		return new ResponseEntity<CollectionModel<UserDto>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Endpoint to retrieve an existing User by its ID")
	@RolesAllowed({"ROLE_ADMIN"})
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Integer id) throws Exception{
		User user = this.userService.findById(id);
		UserDto userDto = this.userMapper.convertToDto(user);
		
		Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
		userDto.add(selfLink);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Endpoint to create Users")
	@RolesAllowed({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Optional<UserDto> dto){		
		if (dto.isPresent()) {
			UserDto userDto = dto.get();
			User user = this.userMapper.convertToEntity(userDto);
			List<RoleDto> rolesDto = userDto.getRoles();
			List<Integer> ids = rolesDto.stream().map(RoleDto::getRoleId).collect(Collectors.toList());
			Set<Role> roles = this.roleService.findAllByIds(ids).stream().collect(Collectors.toSet());
			user.setPassword(encoder.encode(userDto.getPassword()));
			user.setRoles(roles);
			this.userService.save(user);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Endpoint to delete an existing User by its ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) throws Exception{
		this.userService.deleteById(id);		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
