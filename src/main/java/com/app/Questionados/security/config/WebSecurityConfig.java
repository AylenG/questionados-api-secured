package com.app.Questionados.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] WHITELIST = { "/", "/index.html", 
												"/css/**", "/img/**",
											    "/api/auth/**", "/v2/api-docs",
											    "/swagger-resources",
											    "/swagger-resources/**",
											    "/configuration/ui",
											    "/configuration/security",
											    "/swagger-ui.html", "/webjars/**",
											    "/v3/api-docs/**", "/swagger-ui/**"};
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication() //
				.dataSource(dataSource) //
				.passwordEncoder(new BCryptPasswordEncoder()) //
				.usersByUsernameQuery("select username, password, enabled from users where username=?") //
				.authoritiesByUsernameQuery("select username, role from users where username=?"); //
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //
			.csrf().disable() //
			.authorizeRequests() //
			.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN") //
			.antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN") //
			.antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN") //
			.antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER") //
			.antMatchers(WHITELIST).permitAll()
			.anyRequest().authenticated() //
			.and() //
			.httpBasic(); //
	}
}
