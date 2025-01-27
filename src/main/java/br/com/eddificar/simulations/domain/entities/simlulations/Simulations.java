package br.com.eddificar.simulations.domain.entities.simlulations;

import br.com.eddificar.simulations.domain.entities.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Simulations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double mortgageValue;
    private Double realtyPrice;
    private Integer duration;
    private Double downPayment;
    private Integer partnerId;
    private String source;
    private Boolean userRequestedContact;
    private Boolean hasFgts;
    private Double fgtsValue;

    @Embedded
    private User user;

    List<String> finality;


}

