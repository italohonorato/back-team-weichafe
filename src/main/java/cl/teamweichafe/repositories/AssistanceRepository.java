package cl.teamweichafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.teamweichafe.domain.Assistance;
import cl.teamweichafe.domain.AssistancePk;

public interface AssistanceRepository extends JpaRepository<Assistance, AssistancePk> {

}
