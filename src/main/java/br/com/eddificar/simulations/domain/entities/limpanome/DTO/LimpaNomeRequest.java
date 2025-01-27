package br.com.eddificar.simulations.domain.entities.limpanome.DTO;

public record LimpaNomeRequest(

        Long id,
        String fullName,
        String cpf,
        String cellPhoneNumber
) {
}
