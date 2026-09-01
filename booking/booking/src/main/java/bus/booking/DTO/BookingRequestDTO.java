package bus.booking.DTO;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDTO {
    private LocalDate bookingDate;
    @Min(1)
    private int seatsBooked;
    @NotNull
    private Long busId;
    @NotNull
    private Long passengerId;
}
