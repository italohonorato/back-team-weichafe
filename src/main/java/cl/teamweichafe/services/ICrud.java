package cl.teamweichafe.services;

import java.util.List;

public interface ICrud<E> {

	E findById(Integer id) throws Exception;
	List<E> findall();
	E save(E e);
	boolean deleteById(Integer id) throws Exception;
}
