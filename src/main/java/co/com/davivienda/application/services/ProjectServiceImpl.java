package co.com.davivienda.application.services;

import co.com.davivienda.application.dtos.ProjectDto;
import co.com.davivienda.application.mappers.ProjectMapper;
import co.com.davivienda.application.ports.input.ProjectInPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectInPort projectUseCaseImp;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto findById(Long id) {
         return projectUseCaseImp.findById(id)
                 .map(projectMapper::toProjectDto)
                 .orElse(null);
    }

    @Override
    public ProjectDto findByName(String name) {
        return projectUseCaseImp.findByName(name)
                .map(projectMapper::toProjectDto)
                .orElse(null);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectUseCaseImp.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .toList();
    }

    @Override
    public ProjectDto save(ProjectDto dto) {
        return projectMapper
                .toDtoFromDomain(
                        projectUseCaseImp.save(projectMapper.toDomainFromDto(dto))
                );
    }

    @Override
    public ProjectDto update(ProjectDto dto) {
        return projectMapper
                .toDtoFromDomain(
                        projectUseCaseImp.update(projectMapper.toDomainFromDto(dto))
                );
    }

    @Override
    public void delete(Long id) {
        projectUseCaseImp.delete(id);
    }
}
