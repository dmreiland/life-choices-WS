//package edu.sjsu.cmpe283.lifechoices.config;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.stereotype.Controller;
//
///**
// * User: maksim
// * Date: 3/5/14 - 9:36 PM
// */
//@Controller
//@EnableAutoConfiguration
//@EnableGlobalAuthentication
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/v1/geo").fullyAuthenticated()
//                .anyRequest().anonymous();
////        http
////                .formLogin()
//////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("rob").password("rob").roles("USER", "ADMIN").and()
//                .withUser("sai").password("sai").roles("ADMIN").and()
//                .withUser("max").password("max").roles("USER");
//    }
//
//
//}
