package book.service.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Message to send to the user")
public class MessageResponse {
    @Schema(description = "Text of the message")
    private String message;
}
