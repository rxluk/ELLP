package com.app.ellp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EllpApplication {

	public static void main(String[] args) {
		// Carregar variáveis do .env antes de iniciar o Spring Boot
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		// Verificar se a variável foi carregada corretamente
		System.out.println("SERVER_PORT carregado: " + System.getProperty("SERVER_PORT"));

		SpringApplication.run(EllpApplication.class, args);
	}
}