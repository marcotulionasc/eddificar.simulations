package br.com.eddificar.simulations.domain.entities.simlulations.DTO;

import br.com.eddificar.simulations.domain.entities.user.DTO.UserDTO;

import java.lang.reflect.Array;
import java.util.List;

public record SimulationsRequestDTO(
    String type,
    Double mortgageValue,
    Double realtyPrice,
    Integer duration,
    Double downPayment,
    Integer partnerId,
    String source,
    Boolean userRequestedContact,
    Boolean hasFgts,
    Double fgtsValue,
    UserDTO user,
    List<String> finality

) {

}
