package br.com.samueljunnior.module.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 9008974409610935043L;

    private String sender;
    private String email;
    private String subject;
    private String resourceTemplate;
    private Map<String, String> paramReplaced;
}
