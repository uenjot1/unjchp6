package unj.example.unjchp6.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Unjchp6AuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private Unjchp6UserDetailsService userService;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String password= authentication.getCredentials().toString();
		String username = authentication.getName();
		
		UserDetailsAdapter userDetails = userService.loadUserByUsername(username);
		
		 switch (userDetails.getUser().getAlgorithm()) {
         case BCRYPT:
             return checkPassword(userDetails, password, bCryptPasswordEncoder);
         case SCRYPT:
             return checkPassword(userDetails, password, sCryptPasswordEncoder);
     }

     throw new  BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		   return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	  private Authentication checkPassword(UserDetails user, String rawPassword, PasswordEncoder encoder) {
	        if (encoder.matches(rawPassword, user.getPassword())) {
	            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
	        } else {
	            throw new BadCredentialsException("Bad credentials");
	        }
	    }

}
