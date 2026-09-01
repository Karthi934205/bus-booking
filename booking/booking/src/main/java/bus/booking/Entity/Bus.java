package bus.booking.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    private String busNumber;
    private String source;
    private String destination;
    private java.time.LocalTime departureTime;
    private java.time.LocalTime arrivalTime;
    private int totalSeats;
    private int seatsAvailable;

}
