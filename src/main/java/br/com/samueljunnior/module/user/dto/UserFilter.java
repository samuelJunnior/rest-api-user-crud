package br.com.samueljunnior.module.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserFilter implements Serializable {
    @Serial
    private static final long serialVersionUID = 2034408011263072695L;

    private String name;
    private String email;
    private String cpf;
}
