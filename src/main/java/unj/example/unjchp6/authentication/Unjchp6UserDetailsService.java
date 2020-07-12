package unj.example.unjchp6.authentication;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import unj.example.unjchp6.model.User;
import unj.example.unjchp6.repository.UserRepository;

@Service
public class Unjchp6UserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetailsAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
		User user = repository.findUserByUsername(username).orElseThrow(s);
		return new UserDetailsAdapter(user);
	}

}
