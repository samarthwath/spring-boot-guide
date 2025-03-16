package com.learn.security.config;

import com.learn.security.config.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableMethodSecurity Annotation will enable @PreAuthorize annotation which we have used at the method level.
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        //httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return httpSecurity.build();
//    }

//    public SecurityFilterChain imperativeFilterChain(HttpSecurity httpSecurity) throws Exception {
//        Customizer<CsrfConfigurer<HttpSecurity>> customizerCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//        httpSecurity.csrf(customizerCsrf);
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disabling CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/admin")
                        .hasRole("ADMIN")
                        .requestMatchers("/home/normal")
                        .hasRole("NORMAL_USER")
                        .requestMatchers("/home/public").permitAll()// Allowing public access
                        .requestMatchers("/register-user").permitAll()
                        .anyRequest().authenticated() // Securing all other endpoints
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails firstUser = User
                .withUsername("samarth")
                .password(passwordEncoder().encode("samarth"))
                .roles("NORMAL_USER")
                .build();
        UserDetails secondUser = User
                .withUsername("samarth1")
                .password(passwordEncoder().encode("samarth1"))
                .roles("ADMIN")
                .build();
        UserDetails thirdUser = User
                .withUsername("samarth2")
                .password(passwordEncoder().encode("samarth2"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(firstUser, secondUser, thirdUser);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }
}
