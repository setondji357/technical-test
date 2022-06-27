package technical.test.api.client;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientDto clientToStudentDto(
            Client client
    );
    Client clientDtoToClient(
            ClientDto clientDto
    );
}

