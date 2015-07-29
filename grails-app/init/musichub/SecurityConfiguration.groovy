package musichub

import com.musichub.security.filters.CsrfHeaderFilter
import com.musichub.security.handlers.NoRedirectLogoutSuccessHandler

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource

	@Autowired
	public UserDetailsService userDetailsService

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf()
			.csrfTokenRepository(csrfTokenRepository())
			.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
		http
			.userDetailsService(userDetailsService)
			.httpBasic()

		// Unprotected Resources
		http
			.authorizeRequests()
				.antMatchers(
					HttpMethod.GET,
					"/tracks/*",
					"/discs/*",
					"/bands/*",
					"/bars/*",
					"/artists/*",
					"/events/*",
					"/postulates/*",
					"/photos/*",
					"/videos/*",
					"/quotes/*",
					"/countries/*",
					"/roles/*",
					"/assets/*",
					"/"
				).permitAll()

				.antMatchers(
					HttpMethod.GET,
					"/profile/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BAR') or hasRole('ROLE_ARTIST')")

				.antMatchers(
					HttpMethod.DELETE,
					"/bars/*",
					"/artists/*",
					"/countries/*",
					"/profile/*",
					"/quotes/*",
					"/roles/*",
				).access("hasRole('ROLE_ADMIN')")

				.antMatchers(
					HttpMethod.DELETE,
					"/events/*",
					"/postulates/*",
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.DELETE,
					"/bands/*",
					"/tracks/*",
					"/discs/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ARTIST')")

				.antMatchers(
					HttpMethod.DELETE,
					"/videos/*",
					"/photos/*"
				).access("hasRole('ROLE_ARTIST') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.PUT,
					"/bars/*",
					"/artists/*",
					"/countries/*",
					"/quotes/*",
					"/roles/*"
				).access("hasRole('ROLE_ADMIN')")

				.antMatchers(
					HttpMethod.PUT,
					"/profile/",
					"/postulates/*",
					"/tracks/*",
					"/discs/*",
					"/videos/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BAR') or hasRole('ROLE_ARTIST')")

				.antMatchers(
					HttpMethod.PUT,
					"/bands/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ARTIST')")

				.antMatchers(
					HttpMethod.PUT,
					"/events/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.PUT,
					"/photos/*"
				).access("hasRole('ROLE_ARTIST') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.POST,
					"/users/register",
					"/resetPassword/*",
					"/confirmToken/*"
				).permitAll()

				.antMatchers(
					HttpMethod.POST,
					"/users/changePassword"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ARTIST') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.POST,
					"/bands/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ARTIST')")

				.antMatchers(
					HttpMethod.POST,
					"/events/*"
				).access("hasRole('ROLE_ADMIN') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.POST,
					"/photos/*",
					"/postulates/*",
					"/videos/",
					"/tracks/*",
					"/discs/*"
				).access("hasRole('ROLE_ARTIST') or hasRole('ROLE_BAR')")

				.antMatchers(
					HttpMethod.POST,
					"/bars/*",
					"/artists/*",
					"/countries/*",
					"/profile/",
					"/quotes/",
					"/roles/"
				).access("hasRole('ROLE_ADMIN')")

		http.logout().logoutSuccessHandler(new NoRedirectLogoutSuccessHandler())
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
	}
}