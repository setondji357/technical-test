package technical.test.api.client;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Client {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String address;

    public Client(String lastName, String firstName, String email, String phoneNumber, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Client() {
    }
}