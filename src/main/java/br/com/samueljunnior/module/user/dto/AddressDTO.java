package br.com.samueljunnior.module.user.dto;

import br.com.samueljunnior.module.user.enums.UFEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -9091216820464579767L;

    private Long id;
    private String place;
    private String complement;
    private String district;
    private UFEnum ufEnum;

}
