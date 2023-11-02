package br.com.samueljunnior.module.email.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailType {
    EMAIL_WELCOME("/static/templates/EmailWelcomeTemplete.html");
    private final String resource;
}
