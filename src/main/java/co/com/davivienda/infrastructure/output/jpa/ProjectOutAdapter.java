
package co.com.davivienda.infrastructure.output.jpa;

import co.com.davivienda.application.dtos.ProjectDto;
import co.com.davivienda.application.mappers.ProjectMapper;
import co.com.davivienda.application.ports.output.ProjectOutPort;
import co.com.davivienda.infrastructure.output.jpa.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectOutAdapter implements ProjectOutPort {

    private final ProjectRepository repository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto findById(Long id) {
        return repository.findById(id)
                .map(projectMapper::toDtoFromEntity)
                .orElse(null);
    }

    @Override
    public ProjectDto findByName(String name) {
        return projectMapper.toDtoFromEntity(repository.findByName(name));
    }

    @Override
    public List<ProjectDto> findAll() {
        return repository.findAll()
                .stream()
                .map(projectMapper::toDtoFromEntity)
                .toList();
    }

    @Override
    public ProjectDto save(ProjectDto dto) {
        return projectMapper.toDtoFromEntity(
                repository.save(projectMapper.toEntityFromDto(dto))
        );
    }

    @Override
    public ProjectDto update(ProjectDto dto) {
        return projectMapper.toDtoFromEntity(
                repository.save(projectMapper.toEntityFromDto(dto))
        );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
