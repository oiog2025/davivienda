package co.com.davivienda.application.usecases;

import co.com.davivienda.application.mappers.ProjectMapper;
import co.com.davivienda.application.ports.input.ProjectInPort;
import co.com.davivienda.application.ports.output.ProjectOutPort;
import co.com.davivienda.domain.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProjectUseCaseImp implements ProjectInPort {

    private final ProjectOutPort projectOutPort;

    private final ProjectMapper projectMapper;

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.of(projectMapper.toDomainFromDto(projectOutPort.findById(id)));
    }

    @Override
    public Optional<Project> findByName(String name) {
        return Optional.of(projectMapper.toDomainFromDto(projectOutPort.findByName(name)));
    }

    @Override
    public List<Project> findAll() {
        return projectOutPort.findAll().stream()
                .map(projectMapper::toDomainFromDto)
                .collect(Collectors.toList());
    }

    @Override
    public Project save(Project domain) {
        return projectMapper.toDomainFromDto(
                projectOutPort.save(projectMapper.toDtoFromDomain(domain))
        );
    }

    @Override
    public Project update(Project domain) {
        return projectMapper.toDomainFromDto(
                projectOutPort.update(projectMapper.toDtoFromDomain(domain))
        );
    }

    @Override
    public void delete(Long id) {
        projectOutPort.delete(id);
    }
}
