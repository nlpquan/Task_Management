package com.da.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
/**
 * Security Configurations
 * @author ivangudino
 *
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	//security service
	SecurityService service;
		
	/**
	 * This method configures the security settings
	 */
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
			.httpBasic()
				.and()
			.authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and()
		    .authorizeRequests()
		        .antMatchers("/", "/about", "/listing", "/register", "/register/registerLoad").permitAll()
		        .antMatchers("/images/**", "/css/*", "/js/**").permitAll()
		        .anyRequest().authenticated()
		        .and()
		        .formLogin()
		        .loginPage("/login")
		        .usernameParameter("credentials.username")
		        .passwordParameter("credentials.password")
		        .permitAll()
		        .defaultSuccessUrl("/dashboard", true)
		        .and()
		     .logout()
		        .logoutUrl("/logout")
		        .invalidateHttpSession(true)
		        .clearAuthentication(true)
		        .permitAll()
		        .logoutSuccessUrl("/");
	}		
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
