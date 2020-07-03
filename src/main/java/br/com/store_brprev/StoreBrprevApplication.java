package br.com.store_brprev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class StoreBrprevApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(StoreBrprevApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
	}

}
