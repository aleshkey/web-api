package auth.service.controller;


import auth.service.payload.request.LoginRequest;
import auth.service.payload.request.SignupRequest;
import auth.service.payload.response.JWTTokenSuccessResponse;
import auth.service.payload.response.MessageResponse;
import auth.service.service.AuthService;
import auth.service.validator.ResponseErrorValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@Tag(name="Auth Controller", description="This controller is responsible for managing tokens and registering new users.")
public class AuthController {
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    @Operation(summary = "Login user", description = "Login user by email and password")
    @ApiResponse(responseCode = "200", description = "Successful login",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JWTTokenSuccessResponse.class)))
    public ResponseEntity<Object> loginUser( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User credentials", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginRequest.class))) @Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        String jwt = authService.generateToken(loginRequest);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/signup")
    @Operation(summary = "Register user", description = "Register user by user info")
    @ApiResponse(responseCode = "200", description = "User registered successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageResponse.class)))
    public ResponseEntity<Object> registerUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User information", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SignupRequest.class))) @Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        authService.saveUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("user registered successfully"));
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate token", description = "Checking the token for correctness")
    @ApiResponse(responseCode = "200", description = "Token validation result",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JWTTokenSuccessResponse.class)))
    public ResponseEntity<JWTTokenSuccessResponse> validateToken( @Parameter(description = "JWT token", required = true) @RequestParam String token) {
        return ResponseEntity.ok(new JWTTokenSuccessResponse(authService.validateToken(token), token));
    }
}
