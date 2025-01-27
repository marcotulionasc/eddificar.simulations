package br.com.eddificar.simulations.domain.entities.limpanome.DTO;

public record LimpaNomeResponse(

        Long id,
        String fullName,
        String cpf,
        String cellPhoneNumber
) {
}
