package bus.booking.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.Entity.Booking;

import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(long userId);

    List<Booking> findByPassengerPassengerId(Long passengerId);
}