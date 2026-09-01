package bus.booking.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private boolean available;
    private String seatNumber;

    @ManyToOne
    private Bus bus;

}