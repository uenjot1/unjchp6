package unj.example.unjchp6.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@EqualsAndHashCode
public class Product {

	@Id
	private Integer id;

	@NotEmpty
	private String name;
	
	private double price;

	@Enumerated(EnumType.STRING)
	private Currency currency;

}
