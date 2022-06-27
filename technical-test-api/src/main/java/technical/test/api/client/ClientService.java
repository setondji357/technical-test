package technical.test.api.client;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import com.tenancia.mongotest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    public Client addClient(ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        log.info("Creating client with email: {}", clientDto.getEmail());
        return clientRepository.save(client);
    }

    public Client updateClient(String id, ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        log.info("Updating client with id: {}", id);
        return clientRepository.save(client);
    }

    public Client getClientById(String id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        log.info("Getting client with id: {}", id);
        return client;
    }

    public List<Client> getClientByProperties(String lastName, String firstName, String email, Integer page) {
        if(lastName==null && firstName==null   && email==null){
            log.info("Getting all client");
            return clientRepository.findAll();
        }
        log.info("Getting by properties");
        return clientRepository.findClientProperties(lastName, firstName, email,
                PageRequest.of(page, 20));
    }

    public List<Client> findAll() {
        log.info("Getting all client");
        return clientRepository.findAll();
    }

    public void deleteClient(String id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        log.info("Deleting client with id: {}", id);
        clientRepository.deleteById(id);
    }
}