package br.com.samueljunnior.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class LogFooter {
    private static final Logger log = LoggerFactory.getLogger(LogFooter.class);

    public static void showLogFooter(ConfigurableApplicationContext context){
        ConfigurableEnvironment env = context.getEnvironment();

        String serverPort = env.getProperty("server.port");
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n\n***\n\tAplicação {} iniciada com sucesso!\n\tSwagger: http://localhost:{}/swagger-ui.html\n\tDesenvolvido por: Samuel Junnior \n***\n\n", applicationName, serverPort);
    }
}
