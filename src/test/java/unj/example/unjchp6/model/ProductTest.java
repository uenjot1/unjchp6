package unj.example.unjchp6.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class ProductTest {
	
	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldNotRaiseViolationWhenNameIsFilled() {
		Product product = Product.of(1,"product1",1.5d,Currency.USD);
		Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
		assertThat(constraintViolations.size(), is(0));
	}
	
	@Test
	public void shouldRaiseViolationWhenNameIsEmpty() {
		Product product = Product.of(1,"",1.5d,Currency.USD);
		Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
		assertThat(constraintViolations.size(), is(1));
	}

}
