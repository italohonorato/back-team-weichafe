package cl.teamweichafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teamweichafe.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
