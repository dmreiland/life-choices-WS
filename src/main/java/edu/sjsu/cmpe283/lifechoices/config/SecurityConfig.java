package edu.sjsu.cmpe283.lifechoices.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;

/**
 * User: maksim
 * Date: 3/5/14 - 9:36 PM
 */
@Controller
@EnableAutoConfiguration
@EnableGlobalAuthentication
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/v1/geo").fullyAuthenticated()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("useradmin").password("zzzzzz").roles("USER", "ADMIN").and()
                .withUser("admin").password("zzzzzz").roles("ADMIN").and()
                .withUser("user1").password("zzzzzz").roles("USER").and()
                .withUser("user2").password("zzzzzz").roles("USER").and()
                .withUser("user3").password("zzzzzz").roles("USER").and()
                .withUser("user4").password("zzzzzz").roles("USER");
    }


}
