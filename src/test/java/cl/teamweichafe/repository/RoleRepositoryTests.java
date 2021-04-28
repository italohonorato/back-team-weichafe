package cl.teamweichafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.repositories.RoleRepository;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Test
	void saveTest() {
		
		Role role = new Role();
		role.setRoleName("MEMBER");
		
		Role roleSaved = this.roleRepo.save(role);
		
		assertThat(roleSaved)
		.isNotNull()
		.isInstanceOf(Role.class)
		.hasNoNullFieldsOrPropertiesExcept("users")
		.hasFieldOrPropertyWithValue("roleName", "MEMBER");
	}

	@Test
	void findAllTest() {
		
		List<Role> roles = this.roleRepo.findAll();
		
		assertThat(roles)
		.isNotNull()
		.isNotEmpty()
		.isInstanceOf(List.class)
		.hasOnlyElementsOfType(Role.class)
		.hasSize(2);
	}
	
}
