package cl.teamweichafe.services;

import java.util.List;

import cl.teamweichafe.domain.Role;

public interface RoleService extends ICrud<Role> {

	List<Role> findAllByIds(Iterable<Integer> ids);
}
