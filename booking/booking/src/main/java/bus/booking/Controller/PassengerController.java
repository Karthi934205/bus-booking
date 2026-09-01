package bus.booking.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bus.booking.Entity.Passenger;
import bus.booking.Service.PassengerService;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "*")
public class PassengerController {

    @Autowired
    private PassengerService service;

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
        Passenger savedPassenger = service.addPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPassenger);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> passengers = service.getPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable long id) {
        Passenger passenger = service.getPassenger(id);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger,
                                                     @PathVariable long id) {
        Passenger updatedPassenger = service.update(passenger, id);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable long id) {
        String message = service.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/username/{username}")
    public Passenger getPassengerByUsername(
            @PathVariable String username
    ){
        return service.getPassengerByUsername(username);
    }
}
