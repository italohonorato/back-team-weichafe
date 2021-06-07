package cl.teamweichafe.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.AdditionalInfo;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.AdditionalInfoDto;
import cl.teamweichafe.mapper.impl.AdditionalInfoMapper;
import cl.teamweichafe.services.AdditionalInfoService;
import cl.teamweichafe.services.UserService;

@RestController
@RequestMapping("/api/additionalInfo")
public class AdditionalInfoController {

	@Autowired
	private AdditionalInfoService additionalInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdditionalInfoMapper addInfoMapper;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> addAdditionalInfo(@RequestBody Optional<AdditionalInfoDto> infoDto, @PathVariable Optional<Integer> id) throws Exception{
		
		if (!infoDto.isPresent() || !id.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (id.get() <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = this.userService.findById(id.get());
		
		infoDto.ifPresent(dto -> {			
			AdditionalInfo additionalInfo = this.addInfoMapper.convertToEntity(dto);
			additionalInfo.setUser(user);
			this.additionalInfoService.save(additionalInfo);
			});
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AdditionalInfoDto> getAdditionalInfo(@PathVariable Integer id) throws Exception{
		AdditionalInfo addInfo = this.additionalInfoService.findById(id);
		AdditionalInfoDto dto = this.addInfoMapper.convertToDto(addInfo);
		
		Link addInfoRel = linkTo(methodOn(AdditionalInfoController.class).getAdditionalInfo(id)).withSelfRel();
		Link userRel = linkTo(methodOn(UserController.class).getById(addInfo.getUser().getUserId())).withRel("user");
		dto.add(addInfoRel);		
		dto.getUser().add(userRel);	
		
		return new ResponseEntity<AdditionalInfoDto>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Optional<Integer> id, @RequestBody Optional<AdditionalInfoDto> dto) throws Exception{
		
		if (dto.isPresent() && id.isPresent() && id.get() > 0) {
			AdditionalInfo originalAddInfo = this.additionalInfoService.findById(id.get());
			originalAddInfo.setAddress(dto.get().getAddress() != null ? dto.get().getAddress() : originalAddInfo.getAddress());
			originalAddInfo.setDob(dto.get().getDob() != null ? dto.get().getDob() : originalAddInfo.getDob());
			originalAddInfo.setEmail(dto.get().getEmail() != null ? dto.get().getEmail() : originalAddInfo.getEmail());
			originalAddInfo.setPhone(dto.get().getPhone() != null ? dto.get().getPhone() : originalAddInfo.getPhone());
			
			AdditionalInfo additionalInfoUpd = this.additionalInfoService.save(originalAddInfo);
			AdditionalInfoDto dtoUpd = this.addInfoMapper.convertToDto(additionalInfoUpd);
			
			return new ResponseEntity<AdditionalInfoDto>(dtoUpd, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
