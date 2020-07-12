package unj.example.unjchp6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@Table(name = "um_authority")
public class Authority {

	@Id
	private Integer id;

	private String name;

	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;

}
