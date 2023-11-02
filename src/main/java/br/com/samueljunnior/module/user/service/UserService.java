package br.com.samueljunnior.module.user.service;

import br.com.samueljunnior.client.service.ViaCepService;
import br.com.samueljunnior.core.message.MessagePropertiesEnum;
import br.com.samueljunnior.module.email.dto.EmailDTO;
import br.com.samueljunnior.module.email.enums.EmailType;
import br.com.samueljunnior.module.email.service.EmailService;
import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.entity.AddressEntity;
import br.com.samueljunnior.module.user.entity.UserEntity;
import br.com.samueljunnior.module.user.enums.UFEnum;
import br.com.samueljunnior.module.user.mapper.UserCreateMapper;
import br.com.samueljunnior.module.user.mapper.UserMapper;
import br.com.samueljunnior.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserCreateMapper userCreateMapper;
    private final ViaCepService viaCepService;
    private final EmailService emailService;

    @Value("${email.sender}")
    private String sender;

    @Value("${email.subject}")
    private String subject;

    public List<UserDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    public UserDTO findUser(Long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(MessagePropertiesEnum.API_NOT_FOUND::businessException)
        );
    }

    private UserEntity findUserEntity(Long id){
        return repository.findById(id)
                .orElseThrow(MessagePropertiesEnum.API_NOT_FOUND::businessException);
    }

    public UserDTO createUser(UserCreateDTO dto){
        final var cep = viaCepService.findCep(StringUtils.getDigits(dto.getCep()));

        final var address = AddressEntity.builder()
                .place(cep.getLogradouro())
                .complement(cep.getComplemento())
                .district(cep.getBairro())
                .ufEnum(UFEnum.valueOf(cep.getUf()))
                .build();

        var ent = userCreateMapper.toEntity(dto);
        ent.setAddress(address);

        ent = repository.save(ent);

        emailService.sendEmailsHtml(List.of(
                EmailDTO.builder()
                        .resourceTemplate(EmailType.EMAIL_WELCOME.getResource())
                        .email(ent.getEmail())
                        .sender(sender)
                        .subject(subject)
                        .paramReplaced(Map.of("(Name)", ent.getName()))
                        .build()
        ));

        return mapper.toDto(ent);
    }

    public void updateUser(Long id, UserDTO dto){
        final var user = this.findUserEntity(id);

        final var ent = mapper.toEntity(dto);
        ent.setId(user.getId());

        repository.save(ent);
    }

    public UserDTO updateAddresByCep(Long id, String cep){
        final var user = this.findUserEntity(id);
        final var addresData = viaCepService.findCep(cep);

        final var newAddress = user.getAddress();
        newAddress.setPlace(addresData.getLogradouro());
        newAddress.setComplement(addresData.getComplemento());
        newAddress.setDistrict(addresData.getBairro());
        newAddress.setUfEnum(UFEnum.valueOf(addresData.getUf()));
        user.setAddress(newAddress);

        return mapper.toDto(repository.save(user));
    }

    public void deleteUser(Long id){
        final var user = this.findUserEntity(id);
        repository.delete(user);
    }

    public void resendEmailSubscription(Long idUser){
        final var user = this.findUserEntity(idUser);

        emailService.sendEmailsHtml(List.of(
                EmailDTO.builder()
                        .resourceTemplate(EmailType.EMAIL_WELCOME.getResource())
                        .email(user.getEmail())
                        .sender(sender)
                        .subject(subject)
                        .paramReplaced(Map.of("(Name)", user.getName()))
                        .build()
        ));
    }

}
