package auth.service.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Token info")
public class JWTTokenSuccessResponse {
    @Schema(description = "Token availability")
    private boolean success;
    @Schema(description = "Token value")
    private String token;
}
