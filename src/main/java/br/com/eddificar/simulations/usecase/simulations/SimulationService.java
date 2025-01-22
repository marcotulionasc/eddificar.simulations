package br.com.eddificar.simulations.usecase.simulations;

import br.com.eddificar.simulations.domain.entities.simlulations.DTO.CredihomeRequestDTO;
import br.com.eddificar.simulations.domain.entities.simlulations.DTO.CredihomeResponseDTO;
import br.com.eddificar.simulations.domain.entities.simlulations.DTO.SimulationsRequestDTO;
import br.com.eddificar.simulations.domain.entities.simlulations.DTO.SimulationsResponseDTO;
import br.com.eddificar.simulations.domain.entities.simlulations.Simulations;
import br.com.eddificar.simulations.domain.entities.user.DTO.UserDTO;
import br.com.eddificar.simulations.domain.entities.user.User;
import br.com.eddificar.simulations.domain.repositories.simulations.SimulationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimulationService {

    private final SimulationRepository simulationRepository;
    private final RestTemplate restTemplate;

    @Value("${credihome.api.url}")
    private String credihomeApiUrl;

    @Value("${credihome.api.token}")
    private String crediHomeApiToken;

    public SimulationService(SimulationRepository simulationRepository, RestTemplate restTemplate) {
        this.simulationRepository = simulationRepository;
        this.restTemplate = restTemplate;
    }

    public SimulationsResponseDTO createSimulation(SimulationsRequestDTO requestDTO) {
        // Mapeia o DTO para a entidade
        Simulations simulationData = mapToEntity(requestDTO);

        // Salva os dados no banco de dados
        simulationData = simulationRepository.save(simulationData);

        // Faz a chamada à API da Credihome
        CredihomeResponseDTO credihomeResponse = callCredihomeApi(mapToCredihomeRequest(simulationData));

        // Mapeia a resposta da Credihome para o DTO interno de resposta
        return mapToResponseDTO(simulationData, credihomeResponse);
    }

    private CredihomeResponseDTO callCredihomeApi(CredihomeRequestDTO credihomeRequestDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(crediHomeApiToken);

            HttpEntity<CredihomeRequestDTO> requestEntity = new HttpEntity<>(credihomeRequestDTO, headers);

            ResponseEntity<CredihomeResponseDTO> response = restTemplate.postForEntity(
                    credihomeApiUrl,
                    requestEntity,
                    CredihomeResponseDTO.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API da Credihome", e);
        }
    }

    private Simulations mapToEntity(SimulationsRequestDTO requestDTO) {
        Simulations simulation = new Simulations();
        simulation.setType(requestDTO.type());
        simulation.setMortgageValue(requestDTO.mortgageValue());
        simulation.setRealtyPrice(requestDTO.realtyPrice());
        simulation.setDuration(requestDTO.duration());
        simulation.setDownPayment(requestDTO.downPayment());
        simulation.setPartnerId(requestDTO.partnerId());
        simulation.setSource(requestDTO.source());
        simulation.setUserRequestedContact(requestDTO.userRequestedContact());
        simulation.setHasFgts(requestDTO.hasFgts());
        simulation.setFgtsValue(requestDTO.fgtsValue());
        simulation.setFinality(requestDTO.finality());

        User user = new User();
        user.setName(requestDTO.user().name());
        user.setEmail(requestDTO.user().email());
        user.setPhone(requestDTO.user().phone());
        user.setCpf(requestDTO.user().cpf());
        user.setDateOfBirth(requestDTO.user().dateOfBirth());
        simulation.setUser(user);

        return simulation;
    }

    private CredihomeRequestDTO mapToCredihomeRequest(Simulations simulation) {
        return new CredihomeRequestDTO(
                simulation.getType(),
                simulation.getMortgageValue(),
                simulation.getRealtyPrice(),
                simulation.getDuration(),
                simulation.getDownPayment(),
                simulation.getPartnerId(),
                simulation.getSource(),
                simulation.getUserRequestedContact(),
                simulation.getHasFgts(),
                simulation.getFgtsValue(),
                new CredihomeRequestDTO.CredihomeUserDTO(
                        simulation.getUser().getName(),
                        simulation.getUser().getEmail(),
                        simulation.getUser().getPhone(),
                        simulation.getUser().getCpf(),
                        simulation.getUser().getDateOfBirth()
                ),
                simulation.getFinality()
        );
    }

    private SimulationsResponseDTO mapToResponseDTO(Simulations simulation, CredihomeResponseDTO credihomeResponse) {
        return new SimulationsResponseDTO(
                simulation.getId(),
                simulation.getType(),
                simulation.getMortgageValue(),
                simulation.getRealtyPrice(),
                simulation.getDuration(),
                simulation.getDownPayment(),
                simulation.getPartnerId(),
                simulation.getSource(),
                simulation.getUserRequestedContact(),
                simulation.getHasFgts(),
                simulation.getFgtsValue(),
                new UserDTO(
                        simulation.getUser().getName(),
                        simulation.getUser().getEmail(),
                        simulation.getUser().getPhone(),
                        simulation.getUser().getCpf(),
                        simulation.getUser().getDateOfBirth()
                ),
                credihomeResponse.results().stream().map(result -> new SimulationsResponseDTO.ResultDTO(
                        result.name(),
                        result.subProduct(),
                        result.value(),
                        result.anualInterestRate(),
                        result.duration(),
                        result.minimumIncome()
                )).toList()
        );
    }
}
