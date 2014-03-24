package edu.sjsu.cmpe283.lifechoices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * User: maksim
 * Date: 3/1/14 - 11:47 AM
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LifeChoicesApp {

    public static void main(String[] args) throws Exception {

        checkIfReadyToStart();

        SpringApplication app = new SpringApplication(LifeChoicesApp.class);
        app.setShowBanner(false);
        app.run(args);
    }

    private static void checkIfReadyToStart() throws FileNotFoundException{
        URL url = LifeChoicesApp.class.getResource("/application.properties");

        if(url == null){
            System.err.println("+-----------------------------------------------+");
            System.err.println("|   Can't access 'application.properties' file. |");
            System.err.println("|   Make sure that file exists before starting  |");
            System.err.println("|   this application.                           |");
            System.err.println("+-----------------------------------------------+");

            throw new FileNotFoundException("application.properties does not exist");
        }

    }

}
