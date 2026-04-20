package com.my.spring.security.config;

import com.my.spring.security.utils.EmployeeUserDetailsService;
import jdk.jfr.Enabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers("/api/v1/employees/register").permitAll()
                            .requestMatchers("/api/v1/welcome").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Below provider is not necessarily required
  /*  @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(EmployeeUserDetailsService employeeUserDetailsService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(employeeUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    }*/


}
