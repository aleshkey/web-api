package gateway.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "email cant be empty")
    private String username;
    @NotEmpty(message = "password cant be empty")
    private String password;
}

