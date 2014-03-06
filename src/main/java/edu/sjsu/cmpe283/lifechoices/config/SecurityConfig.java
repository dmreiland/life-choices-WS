package edu.sjsu.cmpe283.lifechoices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.stereotype.Controller;

/**
 * User: maksim
 * Date: 3/5/14 - 9:36 PM
 */
@Controller
@EnableAutoConfiguration
@EnableGlobalAuthentication
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("useradmin").password("zzzzzz").roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("admin").password("zzzzzz").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user1").password("zzzzzz").roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password("zzzzzz").roles("USER");
        auth.inMemoryAuthentication().withUser("user3").password("zzzzzz").roles("USER");
        auth.inMemoryAuthentication().withUser("user4").password("zzzzzz").roles("USER");
    }


}
