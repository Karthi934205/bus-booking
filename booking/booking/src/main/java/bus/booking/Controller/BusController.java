package bus.booking.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bus.booking.Entity.Bus;
import bus.booking.Service.BusService;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "*")
public class BusController {

    @Autowired
    private BusService service;

    @PostMapping("/")
    public ResponseEntity<Bus> addBus(@RequestBody Bus b) {
        Bus bus = service.addBus(b);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }

    @PostMapping("/addall")
    public ResponseEntity<List<Bus>> addBusList(@RequestBody List<Bus> buses) {
        List<Bus> savedBuses = service.addBusList(buses);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getById(@PathVariable long id) {
        Bus bus = service.getById(id);
        return ResponseEntity.ok(bus);
    }

    @GetMapping("/")
    public ResponseEntity<List<Bus>> getAll() {
        List<Bus> buses = service.getAll();
        return ResponseEntity.ok(buses);
    }

    @PutMapping("/")
    public ResponseEntity<Bus> update(@RequestBody Bus b) {
        Bus updatedBus = service.update(b);
        return ResponseEntity.ok(updatedBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        String message = service.delete(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bus>> search(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam int seats) {

        List<Bus> buses = service.search(source, destination, seats);
        return ResponseEntity.ok(buses);
    }
}