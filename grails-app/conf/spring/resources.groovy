import com.musichub.ApplicationContextHolder;
import com.musichub.security.UserDetailsServiceImpl

import musichub.SecurityConfiguration
import com.musichub.security.GoogleAuth

// Place your Spring DSL code here
beans = {
	webSecurityConfiguration(SecurityConfiguration)
	UserDetailsService(UserDetailsServiceImpl)
	GoogleAuth(GoogleAuth)
	applicationContextHolder(ApplicationContextHolder) { bean ->
		bean.factoryMethod = 'getInstance'
	}
//	OAuth2AuthenticationProcessingFilter(OAuth2AuthenticationProcessingFilter) {
//		authenticationManager = ref("authenticationManager")
//	}
}
