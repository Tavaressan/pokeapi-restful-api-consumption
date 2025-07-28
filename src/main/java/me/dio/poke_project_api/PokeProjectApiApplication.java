package me.dio.poke_project_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL")})
@EnableFeignClients
@SpringBootApplication
public class PokeProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeProjectApiApplication.class, args);
	}

}
