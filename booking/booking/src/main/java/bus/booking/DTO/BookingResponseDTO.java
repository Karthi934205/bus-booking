package bus.booking.DTO;

import lombok.Data;

import java.time.LocalDate;

import bus.booking.Entity.Booking;

@Data
public class BookingResponseDTO
{
    private Long bookingId;
    private String passengerName;
    private String BusNo;
    private String source;
    private String destination;
    private LocalDate bookingDate;
    private int seatsBooked;
    private String status;

    public BookingResponseDTO(Booking booking) {
        bookingId=booking.getBookingId();
        passengerName=booking.getPassenger().getName();
        BusNo=booking.getBus().getBusNumber();
        source=booking.getBus().getSource();
        destination=booking.getBus().getDestination();
        bookingDate=booking.getBookingDate();
        seatsBooked=booking.getSeatsBooked();
        status=booking.getStatus();
    }
}
