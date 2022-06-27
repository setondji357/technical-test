package technical.test.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("{id}")
    public ResponseEntity<?> getClient(
            @PathVariable String id) {
        return ResponseEntity.ok()
                .body(clientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClentByProperties(@RequestParam(required = false) String lastName,
                                                             @RequestParam(required = false) String firstName,
                                                             @RequestParam(required = false) String email,
                                                             @RequestParam(defaultValue ="1") Integer page) {
        // clientService.getClientByProperties(lastName, firstName, email, page)
        return ResponseEntity.ok()
                .body(clientService.findAll());
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.addClient(clientDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable String id, @RequestBody ClientDto clientDto) {
        return ResponseEntity.ok().body(clientService.updateClient(id, clientDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable String id) {
        return ResponseEntity.ok().body(id);
    }
    @PatchMapping("{id}")
    public void patchResource(
            @PathVariable String id,
            @RequestBody Map<String, Object> changes) {

    }
}