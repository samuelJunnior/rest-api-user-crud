package br.com.samueljunnior.core.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientDefaultConfig {

    private static final long CINCO = 5L;
    private static final long UM = 1L;
    private static final int TENTATIVAS = 3;

    public FeignClientDefaultConfig() {
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(CINCO, TimeUnit.SECONDS, CINCO, TimeUnit.SECONDS, true);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(TimeUnit.SECONDS.toMillis(UM), TimeUnit.SECONDS.toMillis(UM), TENTATIVAS);
    }


}
