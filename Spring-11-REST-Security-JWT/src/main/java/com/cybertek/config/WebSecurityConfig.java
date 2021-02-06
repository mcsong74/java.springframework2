package com.cybertek.config;

import com.cybertek.service.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SecurityFilter securityFilter;

    //no login form, need below
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() //to config custom config manually, need to be disabled//
                .authorizeRequests()
                .antMatchers("/authenticate") //
                .permitAll() //all user can access
                .anyRequest()
                .authenticated();

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
