package bus.booking.Repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bus.booking.Entity.Bus;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus,Long> {

    @Query("""
SELECT b
FROM Bus b
LEFT JOIN Booking bk ON bk.bus = b
WHERE b.source = :source
AND b.destination = :destination
GROUP BY b
HAVING b.totalSeats - COALESCE(SUM(bk.seatsBooked), 0) >= :requiredSeats
""")
    List<Bus> findAvailableBuses(@Param("source") String source,
                                 @Param("destination") String destination,
                                 @Param("requiredSeats") int requiredSeats);
}
