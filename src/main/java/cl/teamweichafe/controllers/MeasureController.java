package cl.teamweichafe.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
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

import cl.teamweichafe.domain.Measure;
import cl.teamweichafe.dto.MeasureDto;
import cl.teamweichafe.mapper.FunctionalMapper;
import cl.teamweichafe.services.MeasureService;

@RestController
@RequestMapping("/api/measure")
public class MeasureController {
	
	@Autowired
	private MeasureService measureService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RolesAllowed({"ROLE_ADMIN"})
	@PostMapping
	public ResponseEntity<?> add(@RequestBody @NotNull MeasureDto measureDto) {		

		FunctionalMapper<MeasureDto, Measure> mapper = (dto, classTo) -> this.modelMapper.map(dto, classTo);
		Measure entity = mapper.mapFromTo(measureDto, Measure.class);
		this.measureService.save(entity);		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
	@GetMapping
	public ResponseEntity<List<MeasureDto>> getAll(){
		
		FunctionalMapper<Measure, MeasureDto> mapper = (classFrom, dto) -> this.modelMapper.map(classFrom, dto);
		List<Measure> measures = this.measureService.findall();
		List<MeasureDto> dtos = measures.stream().map(m -> mapper.mapFromTo(m, MeasureDto.class)).collect(Collectors.toList());
		dtos.forEach(dto ->  {
			try {
				Link selfRel = linkTo(methodOn(MeasureController.class).getById(Optional.of(dto.getMeaseurId()))).withSelfRel();
				dto.add(selfRel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return new ResponseEntity<List<MeasureDto>>(dtos, HttpStatus.OK);
	}
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_MEMBER"})
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Optional<Integer> id) throws Exception{
		
		FunctionalMapper<Measure, MeasureDto> mapper = (classFrom, dto) -> this.modelMapper.map(classFrom, dto);
		
		if(id.isPresent() && id.get() > 0) {
			Measure measure = this.measureService.findById(id.get());
			MeasureDto dto = mapper.mapFromTo(measure, MeasureDto.class);
			Link selfRel = linkTo(MeasureController.class).slash(id.get()).withSelfRel();
			dto.add(selfRel);
			
			return new ResponseEntity<MeasureDto>(dto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RolesAllowed({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable @NotNull @Min(1) Integer id) throws Exception{
		this.measureService.deleteById(id);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
