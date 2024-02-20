package gateway.filter;


import gateway.client.AuthServiceClient;
import gateway.payload.response.JWTTokenSuccessResponse;
import gateway.validators.RouteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final RouteValidator validator;

    private final AuthServiceClient authServiceClient;

    public AuthenticationFilter(RouteValidator validator, @Lazy AuthServiceClient authServiceClient) {
        super(Config.class);
        this.validator = validator;
        this.authServiceClient = authServiceClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                String token = authHeader.split(" ")[1];
                JWTTokenSuccessResponse jwtTokenSuccessResponse = authServiceClient.validateToken(token);
                if (!jwtTokenSuccessResponse.isSuccess()) {
                    log.error("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }


    public static class Config {

    }

}
