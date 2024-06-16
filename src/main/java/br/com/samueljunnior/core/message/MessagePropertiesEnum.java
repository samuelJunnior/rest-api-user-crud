package br.com.samueljunnior.core.message;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@RequiredArgsConstructor
public enum MessagePropertiesEnum implements IMessageProperties {

    CORE_ACCESS_DENIED("core.access-denied"),
    CORE_UNDENTIFIED_ERROR("core.unidentified-error"),
    API_NOT_FOUND("api.not-found"),
    API_UNIDENTIFIED_ERROR("api.unidentified-error"),
    VIA_CEP_INTEGRATIONS_ERROR("via-cep.integration_error"),
    ALREADY_REGISTERED_USER("already_registred_user"),
    REPORT_IMAGE_ERROR("report_image_error"),
    USER_REPORT_ERROR("user_report_error");

    private final String key;
    private String[] args = new String[0];

    public String key() {
        return  this.key;
    }

    public String[] getArgs() {
        return this.args;
    }

    public IMessageProperties bind(String... args){
        this.args = ArrayUtils.isNotEmpty(args) ? args : null;
        return  this;
    }
}
