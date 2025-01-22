package br.com.eddificar.simulations.domain.entities.simlulations.DTO;

import java.util.List;

public record CredihomeResponseDTO(
        String leadId,
        int duration,
        double firstPayment,
        List<Result> results
) {
    public record Result(
            double payment,
            double amortization,
            double interest,
            double mip,
            double dfi,
            double tac,
            double value,
            double minimumIncome,
            String name,
            String table,
            int duration,
            double totalMortgageValue,
            double monthlyInterestRate,
            double anualInterestRate,
            String descricao,
            String subProduct
    ) {}
}
