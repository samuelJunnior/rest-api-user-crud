package br.com.samueljunnior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static br.com.samueljunnior.core.LogFooter.showLogFooter;

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final var context = SpringApplication.run(Application.class, args);
        showLogFooter(context);
    }

}
