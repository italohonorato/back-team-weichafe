package cl.teamweichafe.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.dto.RoleDto;
import cl.teamweichafe.mapper.impl.RoleMapper;
import cl.teamweichafe.services.RoleService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@ApiOperation(value = "Endpoint to retrieve all existing Roles")
	@GetMapping
	public ResponseEntity<?> getAll() {
		
		List<Role> roles = this.roleService.findall();
		List<RoleDto> rolesDto = this.roleMapper.convertToDtoList(roles);
		rolesDto.forEach(dto -> {
			Link selfLink;
			try {
				selfLink = linkTo(methodOn(RoleController.class).getById(dto.getRoleId())).withSelfRel();
				dto.add(selfLink);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return new ResponseEntity<List<RoleDto>>(rolesDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Endpoint to retrieve an existing Roles by its ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable @NotNull Integer id) throws Exception {
		
		Role role = this.roleService.findById(id);
		RoleDto roleDto = this.roleMapper.convertToDto(role);
		Link selfLink = linkTo(RoleController.class).slash(id).withSelfRel();
		roleDto.add(selfLink);
		
		return new ResponseEntity<RoleDto>(roleDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Endpoint to create a Role")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Optional<RoleDto> dto){
		
		if (dto.isPresent()) {
			Role role = this.roleMapper.convertToEntity(dto.get());
			Role savedRole = this.roleService.save(role);
			RoleDto savedDto = this.roleMapper.convertToDto(savedRole);
			
			return new ResponseEntity<RoleDto>(savedDto, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Endpoint to delete a Role")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Optional<Integer> id) throws Exception{
		
		if (id.isPresent()) {
			Role role = this.roleService.findById(id.get());
			
			if (role.getUsers().size() > 0) {
				Map<String, String> message = new HashMap<String, String>();
				message.put("message", "In order to delete the desire Role, you must delete the users associated to that Role first.");
				
				return new ResponseEntity<Object>(message, HttpStatus.CONFLICT); 
			}
			
			this.roleService.deleteById(id.get());
			
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT); 
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
