package br.com.eddificar.simulations.adapters.limpanome;

import br.com.eddificar.simulations.domain.entities.limpanome.DTO.LimpaNomeRequest;
import br.com.eddificar.simulations.domain.entities.limpanome.DTO.LimpaNomeResponse;
import br.com.eddificar.simulations.usecase.limpanome.LimpaNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eddificar/v1")
public class LimpaNomeController {

    @Autowired
    private LimpaNomeService limpaNomeService;

    @PostMapping("/limpanome")
    public ResponseEntity<LimpaNomeResponse> createLimpaNome(@RequestBody LimpaNomeRequest requestDTO) {
        LimpaNomeResponse responseDTO = limpaNomeService.saveData(requestDTO);
        return ResponseEntity.ok(responseDTO);

    }
}
