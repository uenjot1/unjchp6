package unj.example.unjchp6.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
public class Product {

	@Id
	private Integer id;

	private String name;
	private double price;

	@Enumerated(EnumType.STRING)
	private Currency currency;

}
