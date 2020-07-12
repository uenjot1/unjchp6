package unj.example.unjchp6.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "um_user")
public class User {

    @Id
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;

	public User(Integer id, String username, String password, EncryptionAlgorithm algorithm) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.algorithm = algorithm;
	}
    
    

}
