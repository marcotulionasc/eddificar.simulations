package br.com.eddificar.simulations.domain.repositories.limpanome;

import br.com.eddificar.simulations.domain.entities.limpanome.LimpaNome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimpaNomeRepository extends JpaRepository<LimpaNome, Long> {
}
