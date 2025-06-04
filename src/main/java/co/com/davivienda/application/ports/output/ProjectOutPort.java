
package co.com.davivienda.application.ports.output;


import co.com.davivienda.application.dtos.ProjectDto;

import java.util.List;

public interface ProjectOutPort {

    ProjectDto findById(Long id);
    ProjectDto findByName(String name);
    List<ProjectDto> findAll();
    ProjectDto save(ProjectDto dto);
    ProjectDto update(ProjectDto dto);
    void delete(Long id);
}
