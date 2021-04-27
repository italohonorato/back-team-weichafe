package cl.teamweichafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teamweichafe.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserName(String userName);
}
