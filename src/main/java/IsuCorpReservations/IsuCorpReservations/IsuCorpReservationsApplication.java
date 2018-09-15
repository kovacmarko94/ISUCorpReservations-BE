package IsuCorpReservations.IsuCorpReservations;

import org.jtransfo.JTransfo;
import org.jtransfo.internal.JTransfoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IsuCorpReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsuCorpReservationsApplication.class, args);
	}

	@Bean
	public JTransfo jTransfo() {
		return new JTransfoImpl();
	}
}
