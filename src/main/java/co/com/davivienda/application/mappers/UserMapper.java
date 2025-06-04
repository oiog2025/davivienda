
package co.com.davivienda.application.mappers;


import co.com.davivienda.application.dtos.UserDto;
import co.com.davivienda.domain.User;
import co.com.davivienda.infrastructure.output.jpa.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDtoFromEntity(UserEntity entity);
    User toDomainFromDto(UserDto dto);
}
