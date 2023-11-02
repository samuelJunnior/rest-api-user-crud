package br.com.samueljunnior.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2191980758475850754L;

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private AddressDTO address;
    private String email;
}
