package com.whitenight.gate.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("user/**").hasAuthority("/user")
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/calendar")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
