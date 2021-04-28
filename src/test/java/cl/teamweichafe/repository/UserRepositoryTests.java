package cl.teamweichafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.teamweichafe.domain.Role;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.repositories.RoleRepository;
import cl.teamweichafe.repositories.UserRepository;

/**
 * 
 * @author italohonorato
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Test
	void testSaveUser() {
		User user = new User();		
		Role role = this.roleRepo.findById(2).get();
		
		user.setUserName("user2");
		user.setPassword("123456");
		user.addRole(role);
		User userSaved = this.userRepo.save(user);
		
		assertThat(userSaved)
		.isNotNull()
		.isInstanceOf(User.class)
		.hasNoNullFieldsOrPropertiesExcept("additionalInfo", "userMeasures")
		.hasFieldOrPropertyWithValue("userName", "user2")
		.hasFieldOrPropertyWithValue("password", "123456");
		
		assertThat(userSaved.getRoles())
		.isNotNull()
		.isNotEmpty()
		.isInstanceOf(Set.class)
		.hasOnlyElementsOfType(Role.class)
		.hasSize(1);
	}

	@Test
	void updateTest() {
		
		 User user = this.userRepo.findById(2).get();
		 user.setPassword(encoder.encode("123456"));
		 
		 User savedEntity = this.userRepo.save(user);
		 
		 assertThat(savedEntity)
		 .isNotNull()
		 .isInstanceOf(User.class)
		 .hasNoNullFieldsOrPropertiesExcept("additionalInfo", "userMeasures");		 
	}
	
	@Test
	void testFindAllUsers() {
		
		List<User> result = this.userRepo.findAll();
		
		assertThat(result)
		.isNotNull()
		.isNotEmpty()
		.isInstanceOf(List.class)
		.hasOnlyElementsOfType(User.class)
		.hasSize(2);
	}
}
