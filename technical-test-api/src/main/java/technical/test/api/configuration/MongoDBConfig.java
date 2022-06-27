package technical.test.api.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@EnableMongoRepositories(basePackages = "technical.test.api.client")
@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private Environment env;
    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

    @Override
    protected String getDatabaseName() {
        return "client_db";
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString(env.getProperty("spring.data.mongodb.uri"));
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}