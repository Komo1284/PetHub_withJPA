package pethub.with_JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithJpaApplication.class, args);
	}

}
