package com.da.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // configure SecurityFilterChain
    @SuppressWarnings({ "removal", "deprecation" })
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable()
        .authorizeRequests()
        .requestMatchers("/api/**").authenticated()
        .requestMatchers("/index").permitAll()
        .requestMatchers("/users").hasRole("ADMIN")
        .and()
        .formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll()
        ).logout(
                logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()

        );
        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
}