package auth.service.service;


import auth.service.model.User;
import auth.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found "+ username)
        );
        return build(user);
    }

    public static User build(User user){
        return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
