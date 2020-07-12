package unj.example.unjchp6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import unj.example.unjchp6.model.Authority;
import unj.example.unjchp6.model.Currency;
import unj.example.unjchp6.model.EncryptionAlgorithm;
import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.model.User;
import unj.example.unjchp6.repository.AuthorityRepository;
import unj.example.unjchp6.repository.ProductRepository;
import unj.example.unjchp6.repository.UserRepository;

@SpringBootApplication
public class Unjchp6Application {

	public static void main(String[] args) {
		SpringApplication.run(Unjchp6Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepo, UserRepository uRepo,AuthorityRepository aRepo) {
		return args -> {
			productRepo.save(Product.of(1, "Chockolate", 10.5, Currency.EUR));
			User user = uRepo.saveAndFlush(User.of(1,"unj", "$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG", EncryptionAlgorithm.BCRYPT,null));
			aRepo.save(Authority.of(1,"READ", user));
			aRepo.save(Authority.of(2,"WRITE", user));
		};
	}

}
