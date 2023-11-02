package br.com.samueljunnior.module.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TB_USUARIO")
public class UserEntity {
    private static final long serialVersionUID = 492775980543211690L;

    public static final String ID_USUARIO = "ID_USUARIO";

    @Id
    @Column(name = ID_USUARIO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String name;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate birthDate;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = AddressEntity.ID_ADDRESS)
    private AddressEntity address;

}

