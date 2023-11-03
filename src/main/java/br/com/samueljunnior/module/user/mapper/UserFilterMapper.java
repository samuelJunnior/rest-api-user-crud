package br.com.samueljunnior.module.user.mapper;

import br.com.samueljunnior.core.mapper.BaseMapper;
import br.com.samueljunnior.module.user.dto.UserFilter;
import br.com.samueljunnior.module.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserFilterMapper extends BaseMapper<UserEntity, UserFilter> {
}
