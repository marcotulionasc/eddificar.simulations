package br.com.eddificar.simulations.domain.entities.user.DTO;

public record UserDTO(
        String name,
        String email,
        String phone,
        String cpf,
        String dateOfBirth
) {
}
