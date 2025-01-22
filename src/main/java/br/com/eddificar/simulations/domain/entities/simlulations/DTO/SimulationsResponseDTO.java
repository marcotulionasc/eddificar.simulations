package br.com.eddificar.simulations.domain.entities.simlulations.DTO;

import br.com.eddificar.simulations.domain.entities.user.DTO.UserDTO;

import java.util.List;

public record SimulationsResponseDTO(
        Long id,
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
        List<ResultDTO> results
) {
    public record ResultDTO(
            String bankName,
            String subProduct,
            Double monthlyPayment,
            Double interestRate,
            Integer duration,
            Double minimumIncome
    ) {}
}
