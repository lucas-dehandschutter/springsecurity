package com.example.springsecurity.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    UserDetailsManager manager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    InitializingBean initializer(UserDetailsManager manager) {
        return () -> {
            UserDetails a1 = User.withDefaultPasswordEncoder().username("a1").password("p1").roles("USER").build();
            manager.createUser(a1);
            UserDetails a2 = User.withUserDetails(a1).username("a2").password("p2").build();
            manager.createUser(a2);
        };
    }

}
