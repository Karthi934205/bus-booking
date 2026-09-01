package bus.booking.Controller;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bus.booking.Entity.Seat;
import bus.booking.Service.SeatService;

import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "*")
public class SeatController {


    @Autowired
    private SeatService service;



    // Get all seats of a bus

    @GetMapping("/bus/{busId}")
    public List<Seat> getSeats(
            @PathVariable Long busId,
            @RequestParam LocalDate date
    ){
        return service.getSeats(busId, date);
    }

    @GetMapping("/generate")
    public String generate(){
        service.generateSeatsForExistingBuses();
        return "Seats generated";
    }



    // Get booked seats for particular date

    @GetMapping("/booked")
    public List<String> bookedSeats(
            @RequestParam Long busId,
            @RequestParam LocalDate date
    ){

        return service.getBookedSeats(busId,date);

    }

}