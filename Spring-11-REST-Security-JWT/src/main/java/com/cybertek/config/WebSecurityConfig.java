package com.cybertek.config;

import com.cybertek.service.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // in order to enable security, need to create this class,
    // 1. extends WebSecurityConfigurerAdapter
    // 2. annotation - @Configuration, @EnableWebSecurity
    // 3. @Override methods
    @Autowired
    private SecurityFilter securityFilter;

    //no login form, need below
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http    //ex. if there is angular, has diff port using it, and csrf need to be disabled
                .csrf()
                .disable() //to config custom config manually, need to be disabled//
                .authorizeRequests()
                .antMatchers("/authenticate", "/create-user") //
                .permitAll() //all user can access
                .anyRequest()
                .authenticated();
        //run security filter before send any api request
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
