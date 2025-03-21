package com.learn.security.config.service;

import com.learn.security.config.principal.UserPrincipal;
import com.learn.security.entity.User;
import com.learn.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info("User object is null in CustomUserDetailsService class.");
            throw new UsernameNotFoundException("User not found !!");
        }
        logger.info("Log store password value: {}", user.getPassword());
        return new UserPrincipal(user);
    }
}
