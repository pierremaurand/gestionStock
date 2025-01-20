package com.opmg.ApiGestionStock;

import com.opmg.ApiGestionStock.role.RoleService;
import com.opmg.ApiGestionStock.utilisateur.UtilisateurService;
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

	@Bean
	public CommandLineRunner runner(RoleService roleService, UtilisateurService utilisateurService) {
		return args -> {
			roleService.init();
			utilisateurService.init();
		};
	}

}
