package com.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService);


    //the following s for TESTING PURPOSE FOR IN MEMORY USERS
//             .inMemoryAuthentication()
//             .withUser("anna")
//             .password("{noop}password")
//             .roles("ADMIN")
//             .and()
//             .withUser("dan")
//             .password("{noop}password")
//             .roles("USER");
 }

//
@Override

protected void configure(HttpSecurity http) throws Exception {

    http

            .authorizeRequests()

            .antMatchers("/admin/**").hasRole("ADMIN")

            .anyRequest().authenticated()

            .and()

            .formLogin()

            .loginPage("/login")

            .usernameParameter("email")

            .permitAll()

            .and()

            .logout()

            .logoutSuccessUrl("/login?logout")

            .permitAll();

}



}
