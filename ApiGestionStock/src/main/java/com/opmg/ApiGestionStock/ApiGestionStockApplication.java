package com.opmg.ApiGestionStock;

import com.opmg.ApiGestionStock.service.InitDbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
@SpringBootApplication
public class ApiGestionStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGestionStockApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner(InitDbService initDbService) {
//		return args -> initDbService.init();
//	}

}
