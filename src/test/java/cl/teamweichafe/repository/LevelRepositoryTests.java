package cl.teamweichafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cl.teamweichafe.domain.Level;
import cl.teamweichafe.repositories.LevelRepository;

/**
 * 
 * @author italohonorato
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class LevelRepositoryTests {

	@Autowired
	private LevelRepository levelRepo;
	
	@Test
	void saveTest() {
		Level level = new Level();
		level.setLevelName("BÁSICO");
		level.setLevelDesc("Clase Karate nivel básico 18:30 - 19:30");
		
		Level savedEntity = this.levelRepo.save(level);
		
		assertThat(savedEntity)
		.isNotNull()
		.isInstanceOf(Level.class)
		.hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("levelName", "BÁSICO");
	}
	
	@Test
	void findAllTest() {
		
		List<Level> levels = this.levelRepo.findAll();
		
		assertThat(levels)
		.isNotNull()
		.isNotEmpty()
		.hasOnlyElementsOfType(Level.class)
		.hasSize(1);
	}

}
