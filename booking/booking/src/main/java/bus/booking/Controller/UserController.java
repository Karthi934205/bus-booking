package bus.booking.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bus.booking.DTO.LoginResponse;
import bus.booking.Entity.User;
import bus.booking.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController
{
    @Autowired
    UserService service;

    @PostMapping("/register")
    public LoginResponse register(@RequestBody User u)
    {
        return service.register(u);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User u) {
        return service.login(u);
    }


}