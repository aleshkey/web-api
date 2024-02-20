package gateway.client;

import gateway.payload.request.LoginRequest;
import gateway.payload.request.SignupRequest;
import gateway.payload.response.JWTTokenSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTH-SERVICE", url = "http://localhost:9000/auth")
public interface AuthServiceClient {
    @PostMapping("/signin")
    ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequest loginRequest);

    @PostMapping("/signup")
    ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest);

    @PostMapping("/validate")
    JWTTokenSuccessResponse validateToken(@RequestParam String token);
}
