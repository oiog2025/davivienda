
package co.com.davivienda.application.mappers;


import co.com.davivienda.application.dtos.ProjectDto;
import co.com.davivienda.domain.Project;
import co.com.davivienda.infrastructure.output.jpa.entities.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectDto toDtoFromEntity(ProjectEntity entity);
    Project toDomainFromDto(ProjectDto dto);

    ProjectDto toDtoFromDomain(Project domain);

    ProjectEntity toEntityFromDto(ProjectDto dto);

    ProjectDto toProjectDto(Project project);
}
