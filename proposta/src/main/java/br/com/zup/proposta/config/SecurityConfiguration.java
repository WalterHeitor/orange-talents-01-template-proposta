package br.com.zup.proposta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
	private static final String[] PUBLIC_MACHERS = {"/h2-console/**", "/propostas/**",
											"/biometrias/**", "/bloqueios/**"
											,"/actuator/**", "/avisos/**"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("---------configurações de autenticação------------");
		http.authorizeRequests(authorizeRequests ->
		        authorizeRequests
		        		.antMatchers(PUBLIC_MACHERS).permitAll()
		                .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_walter-proposta:read")
		                .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_walter-proposta:read")
		                .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_walter-proposta:write")
		                .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_walter-proposta:write")
		                .antMatchers(HttpMethod.GET,"/api/actuator/**").hasAuthority("SCOPE_walter-proposta:read")
		                .anyRequest().denyAll())
		             .csrf().disable()
		             .sessionManagement()
		             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		             .and()
		             .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}
