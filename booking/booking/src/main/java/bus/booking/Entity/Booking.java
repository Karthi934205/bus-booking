package bus.booking.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private LocalDate dateOfTravel;
    private LocalDate bookingDate;
    private int seatsBooked;
    private String status;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}