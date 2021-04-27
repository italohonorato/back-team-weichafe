package cl.teamweichafe.services;

import java.util.List;

public interface ICrudEmbeddedPk<E, K> {

	E findByPk(K pk) throws Exception ;
	List<E> findall();
	E save(E e);
	boolean deleteByPk(K pk) throws Exception;
}
