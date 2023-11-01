package br.com.samueljunnior.module.user.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserEntity, UserDTO> {

    @AfterMapping
    default void afterMappingToEntity(@MappingTarget UserEntity target, UserDTO source) {
        if(Objects.nonNull(source)){
            target.setCpf(StringUtils.getDigits(source.getCpf()));
        }
    }
}
