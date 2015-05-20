import com.musichub.security.UserDetailsServiceImpl;
import musichub.SecurityConfiguration;

// Place your Spring DSL code here
beans = {
	webSecurityConfiguration(SecurityConfiguration)
	UserDetailsService(UserDetailsServiceImpl)
}
