package cl.teamweichafe.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teamweichafe.domain.Level;
import cl.teamweichafe.dto.LevelDto;
import cl.teamweichafe.mapper.impl.LevelMapper;
import cl.teamweichafe.services.LevelService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/level")
public class LevelController {

	@Autowired
	private LevelService levelService;
	
	@Autowired
	private LevelMapper levelMapper;
	
	@ApiOperation(value = "Endpoint to retrieve all level registered")
	@GetMapping
	public ResponseEntity<?> getAll(){
		
		List<Level> levels = this.levelService.findall();
		List<LevelDto> dtos = this.levelMapper.convertToDtoList(levels);
		
		dtos.forEach(d -> {
			try {
				Link link = linkTo(methodOn(LevelController.class).getById(Optional.of(d.getLevelId()))).withSelfRel();
				d.add(link);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return new ResponseEntity<List<LevelDto>>(dtos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Endpoint to retrieve a specific level by its ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Optional<Integer> id) throws Exception{
		
		if (id.isPresent()) {
			Link selfLink = linkTo(LevelController.class).slash(id).withSelfRel();
			Level level = this.levelService.findById(id.get());
			LevelDto dto = this.levelMapper.convertToDto(level);
			dto.add(selfLink);	
			return new ResponseEntity<LevelDto>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@ApiOperation(value = "Endpoint to create a level")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Optional<LevelDto> dto) throws Exception{
		
		if (dto.isPresent()) {
			Level level = this.levelMapper.convertToEntity(dto.get());
			Level savedLevel = this.levelService.save(level);
			LevelDto levelDto = this.levelMapper.convertToDto(savedLevel);
			Link selfRel = linkTo(methodOn(LevelController.class).getById(Optional.of(savedLevel.getLevelId()))).withSelfRel();
			levelDto.add(selfRel);
			
			return new ResponseEntity<LevelDto>(levelDto, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Endpoint to update a specific level by its ID")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Optional<LevelDto> dto, @PathVariable Optional<Integer> id) throws Exception{
		
		if (dto.isPresent() 
				&& id.isPresent()
				&& id.get() > 0) {
			
			Level level = this.levelService.findById(id.get());
			
			if (dto.get().getLevelDesc() != null && !dto.get().getLevelDesc().isEmpty()) {
				level.setLevelDesc(dto.get().getLevelDesc());				
			}
			
			if (dto.get().getLevelName() != null && !dto.get().getLevelName().isEmpty()) {
				level.setLevelName(dto.get().getLevelName());			
			}
			
			Level levelUpdated = this.levelService.save(level);
			LevelDto dtoUpdated = this.levelMapper.convertToDto(levelUpdated);
			Link selfRel = linkTo(methodOn(LevelController.class).getById(Optional.of(levelUpdated.getLevelId()))).withSelfRel();
			dtoUpdated.add(selfRel);
			
			return new ResponseEntity<LevelDto>(dtoUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Endpoint to delete a specific level by its ID")
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Optional<Integer> id) throws Exception{
		
		if (!id.isEmpty() && id.get() > 0) {
			this.levelService.deleteById(id.get());
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
