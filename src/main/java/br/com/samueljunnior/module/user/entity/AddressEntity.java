package br.com.samueljunnior.module.user.entity;

import br.com.samueljunnior.module.user.enums.UFEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ENDERECO")
public class AddressEntity {

    public static final String ID_ADDRESS = "ID_ENDERECO";

    @Id
    @Column(name = ID_ADDRESS)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LONGRADOURO")
    private String place;

    @Column(name = "COMPLEMENTO")
    private String complement;

    @Column(name = "BAIRRO")
    private String district;

    @Column(name = "UF")
    @Enumerated(EnumType.STRING)
    private UFEnum ufEnum;

}
