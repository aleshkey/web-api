package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableWebFlux
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8082", "http://localhost:9000"}, maxAge = 2000,
		allowedHeaders = "*", allowCredentials = "true")
public class LibraryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryGatewayApplication.class, args);
	}

}
