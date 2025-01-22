package br.com.eddificar.simulations.domain.repositories.simulations;

import br.com.eddificar.simulations.domain.entities.simlulations.Simulations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<Simulations, Long> {
}
