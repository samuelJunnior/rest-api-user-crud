package br.com.samueljunnior.module.user.servie;

import br.com.samueljunnior.client.service.ViaCepService;
import br.com.samueljunnior.core.message.MessagePropertiesEnum;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserCreateMapper userCreateMapper;
    private final ViaCepService viaCepService;

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

        final var ent = userCreateMapper.toEntity(dto);
        ent.setAddress(address);

        return mapper.toDto(repository.save(ent));
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

}
