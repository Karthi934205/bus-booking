package bus.booking.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import bus.booking.DTO.BookingRequestFormDTO;
import bus.booking.DTO.BookingResponseDTO;
import bus.booking.Service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("")
    public ResponseEntity<BookingResponseDTO> book(@RequestBody BookingRequestFormDTO b) {

        BookingResponseDTO response = service.book(b);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookingResponseDTO>> getBookings() {

        List<BookingResponseDTO> bookings = service.getBookings();

        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable long id) {

        BookingResponseDTO booking = service.getBooking(id);

        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable long id) {

        String message = service.cancel(id);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUser(
            @PathVariable long userId) {

        List<BookingResponseDTO> bookings =
                service.getBookingsByUser(userId);

        return ResponseEntity.ok(bookings);
    }
}
