package auth.service.service;


import auth.service.constants.SecurityConstants;
import auth.service.model.User;
import auth.service.payload.request.LoginRequest;
import auth.service.payload.request.SignupRequest;
import auth.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public void saveUser(SignupRequest userIn) {
        User user = new User();
        user.setName(userIn.getName());
        user.setEmail(userIn.getEmail());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        repository.save(user);
    }

    public String generateToken(LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return SecurityConstants.TOKEN_PREFIX + jwtService.generateToken(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }


}
