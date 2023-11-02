package br.com.samueljunnior.module.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6134638064256306273L;

    @NotEmpty(message = "{field.required-field}")
    private String name;

    @NotNull(message = "{field.required-field}")
    private LocalDate birthDate;

    @NotEmpty(message = "{field.required-field}")
    @CPF(message = "{field.invalid-cpf}")
    @Size(min = 11, max = 11, message = "{field.invalid-size}")
    private String cpf;

    @NotEmpty(message = "{field.required-field}")
    @Email(message = "field.invalid-email" )
    private String email;

    @Size(min = 8, max = 10, message = "{field.invalid-sizer}")
    private String cep;
}
