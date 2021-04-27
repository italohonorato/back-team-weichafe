package cl.teamweichafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cl.teamweichafe.domain.AdditionalInfo;
import cl.teamweichafe.domain.User;
import cl.teamweichafe.repositories.AdditionalInfoRepository;
import cl.teamweichafe.repositories.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class AdditionalInfoRepositoryTests {
	
	@Autowired
	private AdditionalInfoRepository additionalInfoRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Test
	void saveTest() {
		User userRef = this.userRepo.findById(1).get();
		LocalDate dob = LocalDate.of(1983, 1, 20);
		AdditionalInfo additionalInfo = new AdditionalInfo();
		
		additionalInfo.setAddress("El Sauce 1600");
		additionalInfo.setDob(dob);
		additionalInfo.setEmail("italo@gmail.com");
		additionalInfo.setPhone(982168266);
		additionalInfo.setUser(userRef);
		
		AdditionalInfo savedEntity = this.additionalInfoRepo.save(additionalInfo);
		
		assertThat(savedEntity)
		.isNotNull()
		.isInstanceOf(AdditionalInfo.class)
		.hasNoNullFieldsOrProperties()
		.hasFieldOrPropertyWithValue("email", "italo@gmail.com")
		.hasFieldOrPropertyWithValue("phone", 982168266)
		.hasFieldOrPropertyWithValue("user.userName", "user1");
	}

}
