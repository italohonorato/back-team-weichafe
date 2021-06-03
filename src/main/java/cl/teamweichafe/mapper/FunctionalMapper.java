package cl.teamweichafe.mapper;

@FunctionalInterface
public interface FunctionalMapper<F, T> {

	public T mapFromTo(F from, Class<T> to);
}
