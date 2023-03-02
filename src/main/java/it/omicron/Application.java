package it.omicron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("it.omicron.entities")
@ComponentScan("it.omicron.advices")
@ComponentScan("it.omicron.controllers")
@ComponentScan("it.omicron.config")
@ComponentScan("it.omicron.utility")
@ComponentScan("it.omicron.repository")
@ComponentScan("it.omicron.security")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
