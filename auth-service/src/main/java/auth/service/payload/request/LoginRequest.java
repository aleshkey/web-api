package auth.service.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "User login info")
public class LoginRequest {
    @NotEmpty(message = "email cant be empty")
    @Schema(description = "User email")
    private String username;

    @NotEmpty(message = "password cant be empty")
    @Schema(description = "User password")
    private String password;
}

