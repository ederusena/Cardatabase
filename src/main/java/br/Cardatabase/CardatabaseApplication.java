package br.Cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.Cardatabase.domain.AppUser;
import br.Cardatabase.domain.Car;
import br.Cardatabase.domain.Owner;
import br.Cardatabase.repository.AppUserRepository;
import br.Cardatabase.repository.CarRepository;
import br.Cardatabase.repository.OwnerRepository;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository repository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository userRepository;

	public CardatabaseApplication(CarRepository repository, OwnerRepository ownerRepository,
			AppUserRepository userRepository) {
		this.repository = repository;
		this.ownerRepository = ownerRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Cardatabase application started successfully.");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		repository.save(new Car("Ford", "Mustang", "Red",
				"ADF-1121", 2023, 59000, owner1));
		repository.save(new Car("Nissan", "Leaf", "White",
				"SSJ-3002", 2020, 29000, owner2));
		repository.save(new Car("Toyota", "Prius",
				"Silver", "KKO-0212", 2022, 39000, owner2));

		// Fetch all cars and log to console
		for (Car car : repository.findAll()) {
			logger.info("brand: {}, model: {}",
					car.getBrand(), car.getModel());

		}

		// Username: user, password: user
        userRepository.save(new AppUser("user", 
            "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
        // Username: admin, password: admin
        userRepository.save(new AppUser("admin", 
            "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
	}

}
