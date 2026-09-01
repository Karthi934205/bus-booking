package bus.booking.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>
{

    User findByUsername(String username);

    User findByMobile(long mobile);
}