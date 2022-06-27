package technical.test.api.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends MongoRepository<Client, String>, ClientCustomRepository {

}