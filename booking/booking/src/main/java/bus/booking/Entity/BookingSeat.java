package bus.booking.Entity;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate travelDate;


    @ManyToOne
    private Seat seat;


    @ManyToOne
    private Booking booking;

}