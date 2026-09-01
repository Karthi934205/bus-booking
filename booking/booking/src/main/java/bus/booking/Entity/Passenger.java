package bus.booking.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    private String name;
    private int age;
    private String gender;
    private String mobileNumber;
    private String email;

    public Passenger(String passengerName, String gender, String mobile, int age,String email) {
        this.name=passengerName;
        this.age=age;
        this.gender=gender;
        this.mobileNumber=mobile;
        this.email=email;
    }

    public Passenger()
    {

    }

}