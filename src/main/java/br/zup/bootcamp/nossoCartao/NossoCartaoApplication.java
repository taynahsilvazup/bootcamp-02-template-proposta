package br.zup.bootcamp.nossoCartao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
public class NossoCartaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoCartaoApplication.class, args);
	}

}
