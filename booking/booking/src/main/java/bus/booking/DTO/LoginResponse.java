package bus.booking.DTO;


import lombok.Data;

@Data
public class LoginResponse {

    private String message;
    private String username;
    private long mobile;
    private long id;

    public LoginResponse() {
    }

    public LoginResponse(String message, String username, long mobile, long id) {
        this.message = message;
        this.username = username;
        this.mobile = mobile;
        this.id = id;
    }
}
