package technical.test.api.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import technical.test.api.client.ClientCustomRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Client> findClientProperties(String lastName, String firstName, String email, Pageable page) {
        final Query query = new Query().with(page);
        // query.fields().include("id").include("name");

        final List<Criteria> criteria = new ArrayList<>();
        if (lastName != null && !lastName.isEmpty())
            criteria.add(Criteria.where("lastName").is(lastName));
        if (firstName != null && !firstName.isEmpty())
            criteria.add(Criteria.where("firstName").is(firstName));
        if (email != null && !email.isEmpty())
            criteria.add(Criteria.where("email").is(email));

        int criteriaSize=criteria.size();
        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().orOperator(criteria.toArray(new Criteria[criteriaSize])));
        return mongoTemplate.find(query, Client.class);
    }
}