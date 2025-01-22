package br.com.eddificar.simulations.domain.entities.user;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class User {

    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String dateOfBirth;

}
