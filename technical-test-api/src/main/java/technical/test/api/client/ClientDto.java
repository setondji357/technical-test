package technical.test.api.client;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ClientDto {

    private String id;

    @NotEmpty(message = "Last name should not be null or empty")
    private String lastName;

    @NotEmpty(message = "Last name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @NotEmpty(message = "Phone number should not be null or empty")
    private String phoneNumber;

    private String address;
}
