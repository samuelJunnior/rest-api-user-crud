package br.com.samueljunnior.module.email.service;

import br.com.samueljunnior.module.email.dto.EmailDTO;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmailsHtml(final List<EmailDTO> emails) {
        emails.forEach(email -> {
            final var mimeMessage = this.javaMailSender.createMimeMessage();
            final var messageHelper = new MimeMessageHelper(mimeMessage);

            try {
                messageHelper.setFrom(new InternetAddress(email.getSender()));
                messageHelper.setTo(email.getEmail());
                messageHelper.setSubject(email.getSubject());

                final var body = this.getBodyByTemplate(email.getResourceTemplate(), email.getParamReplaced());
                messageHelper.setText(body, true);

                javaMailSender.send(mimeMessage);
            } catch (Exception e) {
                log.error("Erro ao enviar Email para: {}", email.getEmail(), e);
            }
        });
    }

    protected String getBodyByTemplate(String resource, Map<String, String> paramReplaced) {
        var body = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(resource)), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        final var bodyAttomic = new AtomicReference<>(body);
        paramReplaced.forEach((key, value) -> bodyAttomic.getAndSet(bodyAttomic.get().replace(key, value)));

        return bodyAttomic.get();
    }
}
