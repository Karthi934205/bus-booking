package bus.booking.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bus.booking.Entity.Passenger;
import bus.booking.Exception.ResourceNotFoundException;
import bus.booking.Repository.PassengerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService
{
    @Autowired
    private PassengerRepository repo;

    public Passenger addPassenger(Passenger p)
    {
        return repo.save(p);
    }

    public List<Passenger> getPassengers()
    {
        return repo.findAll();
    }

    public Passenger getPassenger(long id)
    {
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource Not Found for Id "+id));
    }

    public Optional<Passenger> getpassenger(long id)
    {
        Optional<Passenger> p=repo.findById(id);
        if(p.isPresent())
        {
            return p;
        }
        else
        {
            throw new ResourceNotFoundException("no resource found for id"+id);
        }
    }

    public Passenger update(Passenger p, long id)
    {
        Optional<Passenger> pass=repo.findById(id);
        if(pass.isPresent())
        {
            return repo.save(p);
        }
        else
        {
            throw new ResourceNotFoundException("Resource Not found For This id"+id);
        }
    }

    public String delete(long id)
    {
        Optional<Passenger> p=repo.findById(id);
        if(p.isPresent())
        {
            repo.deleteById(id);
            return "data deleted";
        }
        else
        {
            throw new ResourceNotFoundException("Resource Not found For This id"+id);
        }
    }

    public Passenger getPassengerByUsername(String username) {
        Passenger passenger = repo.findByName(username);

        if(passenger == null){
            throw new RuntimeException("Passenger not found");
        }

        return passenger;
    }
}
