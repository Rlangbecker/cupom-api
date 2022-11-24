package br.com.cupomapi.cupomapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CupomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CupomApiApplication.class, args);
	}

}
