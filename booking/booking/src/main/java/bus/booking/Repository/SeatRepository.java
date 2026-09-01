package bus.booking.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.Entity.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findByBusBusId(Long busId);

}
