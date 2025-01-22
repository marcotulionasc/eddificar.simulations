package br.com.eddificar.simulations.adapters.simulations;

import br.com.eddificar.simulations.domain.entities.simlulations.DTO.SimulationsRequestDTO;
import br.com.eddificar.simulations.domain.entities.simlulations.DTO.SimulationsResponseDTO;
import br.com.eddificar.simulations.usecase.simulations.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eddificar/v1")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @PostMapping("/simulations")
    public ResponseEntity<SimulationsResponseDTO> createSimulation(@RequestBody SimulationsRequestDTO requestDTO) {
        SimulationsResponseDTO responseDTO = simulationService.createSimulation(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
