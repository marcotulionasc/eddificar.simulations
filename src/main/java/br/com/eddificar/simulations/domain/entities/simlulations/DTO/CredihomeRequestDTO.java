package br.com.eddificar.simulations.domain.entities.simlulations.DTO;

import java.lang.reflect.Array;
import java.util.List;

public record CredihomeRequestDTO(
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
        CredihomeUserDTO user,
        List<String> finality
) {
    public record CredihomeUserDTO(
            String name,
            String email,
            String phone,
            String cpf,
            String dateOfBirth
    ) {}
}
