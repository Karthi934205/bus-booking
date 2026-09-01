package bus.booking.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.Entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger,Long>
{
    Passenger findByName(String username);

    Passenger findByMobileNumber(String mobileNumber);
}
