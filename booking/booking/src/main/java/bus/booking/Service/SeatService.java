package bus.booking.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bus.booking.Entity.BookingSeat;
import bus.booking.Entity.Bus;
import bus.booking.Entity.Seat;
import bus.booking.Repository.BookingSeatRepository;
import bus.booking.Repository.BusRepository;
import bus.booking.Repository.SeatRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private BookingSeatRepository bookingSeatRepo;

    @Autowired
    private BusRepository busRepo;


    public List<Seat> getSeats(Long busId, LocalDate date) {

        List<Seat> seats = seatRepo.findByBusBusId(busId);

        List<String> bookedSeats =
                bookingSeatRepo
                        .findBySeatBusBusIdAndTravelDate(busId, date)
                        .stream()
                        .map(e -> e.getSeat().getSeatNumber())
                        .toList();

        for (Seat seat : seats) {

            if (bookedSeats.contains(seat.getSeatNumber())) {
                seat.setAvailable(false);
            } else {
                seat.setAvailable(true);
            }
        }

        return seats;
    }


    public List<String> getBookedSeats(Long busId, LocalDate date) {

        List<BookingSeat> booked =
                bookingSeatRepo.findBySeatBusBusIdAndTravelDate(busId, date);

        return booked.stream()
                .map(e -> e.getSeat().getSeatNumber())
                .toList();
    }


    public void generateSeatsForExistingBuses() {

        List<Bus> buses = busRepo.findAll();

        List<Seat> seats = new ArrayList<>();

        for (Bus bus : buses) {

            List<Seat> existingSeats =
                    seatRepo.findByBusBusId(bus.getBusId());

            if (!existingSeats.isEmpty()) {
                continue;
            }

            for (int i = 1; i <= bus.getTotalSeats(); i++) {

                Seat seat = new Seat();

                int row = (i - 1) / 4;
                int number = (i - 1) % 4 + 1;

                char rowName = (char) ('A' + row);

                seat.setSeatNumber(rowName + "" + number);
                seat.setAvailable(true);
                seat.setBus(bus);

                seats.add(seat);
            }
        }

        seatRepo.saveAll(seats);
    }
}
