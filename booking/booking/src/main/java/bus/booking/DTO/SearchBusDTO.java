package bus.booking.DTO;

import lombok.Data;

@Data
public class SearchBusDTO {
    private String source;
    private String destination;
    private int seats;
}