package technical.test.api.client;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientCustomRepository {
    public List<Client> findClientProperties(String lastName, String firstName, String email, Pageable page);
    // public List<Client> findAll();
}
