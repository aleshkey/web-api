package auth.service.payload.request;


import auth.service.annotations.PasswordMatches;
import auth.service.annotations.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
@Schema(description = "User info")
public class SignupRequest {
    @Email(message = "it should be email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    @Schema(description = "User email")
    private String email;
    @NotEmpty(message = "enter your name")
    @Schema(description = "User name")
    private String name;
    @NotEmpty(message = "password is required")
    @Size(min = 8)
    @Schema(description = "User password")
    private String password;
    private String confirmPassword;
}