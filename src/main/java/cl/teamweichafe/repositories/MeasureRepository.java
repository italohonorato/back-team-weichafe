package cl.teamweichafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teamweichafe.domain.Measure;

public interface MeasureRepository extends JpaRepository<Measure, Integer> {

}
