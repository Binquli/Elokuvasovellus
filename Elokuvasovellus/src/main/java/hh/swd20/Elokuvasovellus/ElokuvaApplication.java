	package hh.swd20.Elokuvasovellus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.swd20.Elokuvasovellus.domain.Elokuva;
import hh.swd20.Elokuvasovellus.domain.ElokuvaRepository;
import hh.swd20.Elokuvasovellus.domain.Category;
import hh.swd20.Elokuvasovellus.domain.CategoryRepository;
import hh.swd20.Elokuvasovellus.domain.User;
import hh.swd20.Elokuvasovellus.domain.UserRepository;

@SpringBootApplication
public class ElokuvaApplication {

	private static final Logger log = LoggerFactory.getLogger(ElokuvaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ElokuvaApplication.class, args);
	}
		@Bean
		public CommandLineRunner elokuvaDemo(ElokuvaRepository elokuvaRepository, CategoryRepository crepository, UserRepository urepository) { 
			return (args) -> {
				log.info("save movies");
				Category categoryfantasia = new Category("Fantasia");
				crepository.save(categoryfantasia);
				
				// elokuvien genret
				crepository.save(new Category("Seikkailu"));
				crepository.save(new Category("Kauhu"));
				crepository.save(new Category("Toiminta"));
				crepository.save(new Category("Rikos"));
				crepository.save(new Category("Draama"));
				crepository.save(new Category("Rikos/Draama"));
				crepository.save(new Category("Scifi"));
				crepository.save(new Category("Komedia"));
				
				
				// valmiiksi luodut elokuva oliot
				elokuvaRepository.save(new Elokuva("The Avengers", "Joss Whedon", "Joss Whedon(Screenplay), Zak Penn(Story)", 2012, categoryfantasia));
				elokuvaRepository.save(new Elokuva("Get Out", "Jordan Peele", "Jordan Peele", 2017, crepository.findByName("Kauhu").get(0)));	
				elokuvaRepository.save(new Elokuva("The Dark Knight", "Christopher Nolan", "Jonathan Nolan & Christopher Nolan", 2008, crepository.findByName("Toiminta").get(0)));
				elokuvaRepository.save(new Elokuva("Pulp Fiction", "Quentin Tarantino", "Quentin Tarantino & Roger Avary", 1994, crepository.findByName("Rikos").get(0)));
				elokuvaRepository.save(new Elokuva("Interstellar", "Christopher Nolan", "Jonathan Nolan & Christopher Nolan", 2014, crepository.findByName("Scifi").get(0)));
				elokuvaRepository.save(new Elokuva("Joker", "Todd Phillips", "Todd Phillips & Scott Silver", 2019, crepository.findByName("Rikos/Draama").get(0)));
				elokuvaRepository.save(new Elokuva("21 Jump Street", "Phil Lord, Christopher Miller", "Michael Bacall", 2012, crepository.findByName("Komedia").get(0)));
				
				// käyttäjät
				User user1 = new User ("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
				User user2 = new User ("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				User user3 = new User ("testi", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"); // salasana user
				urepository.save(user1);
				urepository.save(user2);
				urepository.save(user3);
				
				log.info("nouda elokuvat");
				for (Elokuva elokuva : elokuvaRepository.findAll()) {
					log.info(elokuva.toString());
				}

			};
	}

}

