package com.mola.authx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * authx
 *
 * @author hatim
 */
@EnableWebFlux
@SpringBootApplication
public class AuthxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthxApplication.class, args);
    }

}
