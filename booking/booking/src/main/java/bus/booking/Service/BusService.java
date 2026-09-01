package bus.booking.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bus.booking.Entity.Bus;
import bus.booking.Entity.Seat;
import bus.booking.Exception.ResourceNotFoundException;
import bus.booking.Repository.BusRepository;
import bus.booking.Repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository repo;

    @Autowired
    private SeatRepository seatRepo;


    public Bus addBus(Bus bus) {

        Bus savedBus = repo.save(bus);

        generateSeats(savedBus);

        return savedBus;
    }


    public List<Bus> addBusList(List<Bus> buses) {

        List<Bus> savedBuses = repo.saveAll(buses);

        List<Seat> seats = new ArrayList<>();

        for(Bus bus : savedBuses) {

            for(int i = 1; i <= bus.getTotalSeats(); i++) {

                Seat seat = new Seat();

                int row = (i - 1) / 4;
                int number = (i - 1) % 4 + 1;

                char rowName = (char)('A' + row);

                seat.setSeatNumber(rowName + "" + number);
                seat.setAvailable(true);
                seat.setBus(bus);

                seats.add(seat);
            }
        }

        seatRepo.saveAll(seats);

        return savedBuses;
    }


    private void generateSeats(Bus bus) {

        List<Seat> seats = new ArrayList<>();

        for(int i = 1; i <= bus.getTotalSeats(); i++) {

            Seat seat = new Seat();

            int row = (i - 1) / 4;
            int number = (i - 1) % 4 + 1;

            char rowName = (char)('A' + row);

            seat.setSeatNumber(rowName + "" + number);
            seat.setAvailable(true);
            seat.setBus(bus);

            seats.add(seat);
        }

        seatRepo.saveAll(seats);
    }


    public Bus getById(long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("no data found on the given id"));
    }


    public List<Bus> getAll() {

        return repo.findAll();
    }


    public Bus update(Bus b) {

        return repo.save(b);
    }


    public String delete(long id) {

        Optional<Bus> b = repo.findById(id);

        if(b.isPresent()) {

            repo.deleteById(id);
            return "data deleted successfully";

        } else {

            throw new ResourceNotFoundException("data not found for id");
        }
    }


    public List<Bus> search(String source,String destination,int seats) {

        return repo.findAvailableBuses(source,destination,seats);
    }

}
