package bus.booking.DTO;



import lombok.Data;

@Data
public class BusDTO {
    private Long busId;
    private String busNo;
    private String source;
    private String destination;
    private String departure;
    private String arrival;
    private int totalSeats;
}