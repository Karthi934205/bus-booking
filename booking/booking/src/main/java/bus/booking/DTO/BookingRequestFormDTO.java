package bus.booking.DTO;



import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookingRequestFormDTO {
    private Long userId;
    private Long busId;
    private LocalDate travelDate;
    private List<Long> seatIds;
    private String passengerName;
    private int age;
    private String gender;
    private String mobileNumber;
    private String email;
}
