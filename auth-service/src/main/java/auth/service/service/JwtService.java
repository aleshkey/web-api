package auth.service.service;


import auth.service.constants.SecurityConstants;
import auth.service.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public static final Logger LOG = LoggerFactory.getLogger(JwtService.class);

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        String userId = Long.toString(user.getId());
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", userId);
        claimsMap.put("name", user.getName());
        claimsMap.put("email", user.getEmail());
        return Jwts.builder().setSubject(userId).addClaims(claimsMap)
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }
        catch (SignatureException |
               MalformedJwtException |
               ExpiredJwtException |
               UnsupportedJwtException |
               IllegalArgumentException exception){
            LOG.error(exception.getMessage());
            return false;
        }
    }

}
