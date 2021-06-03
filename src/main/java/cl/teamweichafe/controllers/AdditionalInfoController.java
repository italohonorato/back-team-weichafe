package cl.teamweichafe.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.AdditionalInfo;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.dto.AdditionalInfoDto;
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
	private ModelMapper modelMapper;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> addAdditionalInfo(@RequestBody AdditionalInfoDto infoDto, @PathVariable Optional<Integer> id) throws Exception{
		Optional<AdditionalInfo> optAddInfo = Optional.of(this.modelMapper.map(infoDto, AdditionalInfo.class));
		
		if (!id.isPresent() || !optAddInfo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = this.userService.findById(id.get());
		optAddInfo.ifPresent(addInfo -> {
			addInfo.setUser(user);
			this.additionalInfoService.save(addInfo);
			});
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AdditionalInfoDto> getAdditionalInfo(@PathVariable Integer id) throws Exception{
		AdditionalInfo addInfo = this.additionalInfoService.findById(id);
		AdditionalInfoDto dto = this.modelMapper.map(addInfo, AdditionalInfoDto.class);
		
		return new ResponseEntity<AdditionalInfoDto>(dto, HttpStatus.OK);
	}
	
}
