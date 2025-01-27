package br.com.eddificar.simulations.usecase.limpanome;


import br.com.eddificar.simulations.domain.entities.limpanome.DTO.LimpaNomeRequest;
import br.com.eddificar.simulations.domain.entities.limpanome.DTO.LimpaNomeResponse;
import br.com.eddificar.simulations.domain.entities.limpanome.LimpaNome;
import br.com.eddificar.simulations.domain.repositories.limpanome.LimpaNomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimpaNomeService {

    @Autowired
    private LimpaNomeRepository limpaNomeRepository;

    public LimpaNomeResponse saveData(LimpaNomeRequest data) {
        LimpaNome limpaNomeEntity = convertToEntity(data);
        limpaNomeRepository.save(limpaNomeEntity);
        return new LimpaNomeResponse(
                limpaNomeEntity.getId(),
                limpaNomeEntity.getFullName(),
                limpaNomeEntity.getCpf(),
                limpaNomeEntity.getCellPhoneNumber());
    }

    private LimpaNome convertToEntity(LimpaNomeRequest request) {
        LimpaNome entity = new LimpaNome();
        entity.setFullName(request.fullName());
        entity.setCpf(request.cpf());
        entity.setCellPhoneNumber(request.cellPhoneNumber());
        return entity;
    }
}

