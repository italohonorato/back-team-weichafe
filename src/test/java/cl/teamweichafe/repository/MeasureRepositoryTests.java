package cl.teamweichafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cl.teamweichafe.domain.Measure;
import cl.teamweichafe.repositories.MeasureRepository;
/**
 * 
 * @author italohonorato
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class MeasureRepositoryTests {

	@Autowired
	private MeasureRepository measureRepo;
	
	@Test
	void saveTest() {
		Measure measure = new Measure();
		measure.setMeasureDesc("Estatura");
		measure.setUnit("CM");
		
		Measure savedEntity = this.measureRepo.save(measure);
		
		assertThat(savedEntity)
		.isNotNull()
		.isInstanceOf(Measure.class)
		.hasNoNullFieldsOrPropertiesExcept("userMeasures")
		.hasFieldOrPropertyWithValue("measureDesc", "Estatura")
		.hasFieldOrPropertyWithValue("unit", "CM");
	}

	@Test
	void findAllTest() {
		
		List<Measure> measures = this.measureRepo.findAll();
		
		assertThat(measures)
		.isNotNull()
		.isNotEmpty()
		.isInstanceOf(List.class)
		.hasOnlyElementsOfType(Measure.class)
		.hasSize(2);
	}
}
