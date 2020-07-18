package unj.example.unjchp6.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import unj.example.authentication.SecurityConfiguration;
import unj.example.unjchp6.model.Authority;
import unj.example.unjchp6.model.Currency;
import unj.example.unjchp6.model.EncryptionAlgorithm;
import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.model.User;
import unj.example.unjchp6.repository.AuthorityRepository;
import unj.example.unjchp6.repository.ProductRepository;
import unj.example.unjchp6.repository.UserRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Import(SecurityConfiguration.class)
@EnableJpaRepositories(basePackages = "unj.example.unjchp6.repository")
@EntityScan(basePackages = "unj.example.unjchp6.model")
@ComponentScan(basePackages =  {"unj.example.unjchp6.controller", "unj.example.unjchp6.service"} )
public class Unjchp6Application {

	public static void main(String[] args) {
		SpringApplication.run(Unjchp6Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepo, UserRepository uRepo,AuthorityRepository aRepo) {
		return args -> {
			productRepo.save(Product.of(1, "Chockolate", 10.5, Currency.EUR));
			User user = uRepo.saveAndFlush(User.of(1,"user", "$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG", EncryptionAlgorithm.BCRYPT,null));
			aRepo.save(Authority.of(1,"READ", user));
			aRepo.save(Authority.of(2,"WRITE", user));
			User reader = uRepo.saveAndFlush(User.of(2,"reader", "$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG", EncryptionAlgorithm.BCRYPT,null));
			aRepo.save(Authority.of(1,"READ", reader));
		};
	}

}
