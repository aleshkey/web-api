package auth.service.constants;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/auth/**";

    public static final long EXPIRATION_TIME = 60_000*60*24;

    public static final String SECRET = "SecretKeyGenJWT";

    public static final String TOKEN_PREFIX ="Bearer ";

}
