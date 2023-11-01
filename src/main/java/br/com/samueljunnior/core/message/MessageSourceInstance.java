package br.com.samueljunnior.core.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

@RequiredArgsConstructor
public class MessageSourceInstance {

    private final MessageSource messageSource;
    private static MessageSourceInstance instance;

    public static synchronized MessageSourceInstance getInstance(){
        if(Objects.isNull(instance)){
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setCacheSeconds(3600);
            messageSource.setDefaultLocale(Locale.getDefault());
            messageSource.setBasenames("core/messages", "api/messages");
            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
            messageSource.setFallbackToSystemLocale(true);
            instance = new MessageSourceInstance(messageSource);
        }
        return instance;
    }

    public String message(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String message(String key, String... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
