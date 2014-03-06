package edu.sjsu.cmpe283.lifechoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * User: maksim
 * Date: 3/1/14 - 11:47 AM
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LifeChoicesApp {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(LifeChoicesApp.class);
        app.setShowBanner(false);
        app.run(args);
    }
}
