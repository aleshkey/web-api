package library.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@CrossOrigin(origins = "http://localhost:8080", maxAge = 2000,
		allowedHeaders = "*", allowCredentials = "true")
@OpenAPIDefinition(info =
	@Info(title = "Library API", version = "1.0", description = "Documentation library API v1.0")
)
public class LibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}

}
