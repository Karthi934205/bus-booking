package bus.booking.Service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bus.booking.DTO.LoginResponse;
import bus.booking.Entity.User;
import bus.booking.Repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository repo;

    public LoginResponse register(User u) {

        LoginResponse lr=new LoginResponse("",u.getUsername(),u.getMobile(),0);

        User mobileUser = repo.findByMobile(u.getMobile());

        if (mobileUser != null) {
            lr.setMessage("Mobile number already registered");
            return lr;
        }

        User usernameUser = repo.findByUsername(u.getUsername());

        if (usernameUser != null) {
            lr.setMessage("Username already exists");
            return lr;
        }

        repo.save(u);

        lr.setMessage("Registered successfully");

        return lr;
    }


    public LoginResponse login(User u) {

        LoginResponse lr=new LoginResponse("",u.getUsername(),u.getMobile(),0);

        User dbUser = repo.findByUsername(u.getUsername());

        if (dbUser == null) {
            lr.setMessage("User not found");
            return lr;
        }

        if (!dbUser.getPassword().equals(u.getPassword())) {
            lr.setMessage("Wrong password");
            return lr;
        }

        lr.setMessage("Login success");
        lr.setUsername(dbUser.getUsername());
        lr.setMobile(dbUser.getMobile());
        lr.setId(dbUser.getId());

        return lr;
    }
}
