package musichub


import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


//@ComponentScan
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application)
    }

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
  
	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  private TokenStore tokenStore;

	  @Override
	  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore)
			.authenticationManager(authenticationManager)
	  }

	  @Override
	  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		  clients.inMemory()
		  	.withClient("web")
			.secret("secret")
			.authorizedGrantTypes("password")
			.scopes("openid", "read", "write", "trust")
			.resourceIds("web")
			.accessTokenValiditySeconds(7200)
	  }
	  @Bean
	  public TokenStore tokenStore() {
		  return new InMemoryTokenStore();
	  }
	}

	@Configuration
	@EnableResourceServer
	protected static class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private TokenStore tokenStore;

		@Autowired
		public UserDetailsService userDetailsService

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {

			// Oauth Token & Protected Resource's Rules
			http
				.userDetailsService(userDetailsService)
				.authorizeRequests()
					.antMatchers("/oauth/token")
						.fullyAuthenticated()
						.and()
							.httpBasic()
							.authenticationEntryPoint(new OAuth2AuthenticationEntryPoint())
						.and()
							.addFilterBefore(new ClientCredentialsTokenEndpointFilter(), BasicAuthenticationFilter.class)
							.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
							.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
			// Protected Resources
//			http
//				.antMatchers(HttpMethod.GET, '/home/**').hasAnyRole('BAR', 'ARTIST')
		}
	}
}