package co.com.davivienda.application.ports.input;


import co.com.davivienda.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectInPort {
    Optional<Project> findById(Long id);

    Optional<Project> findByName(String name);

    List<Project> findAll();

    Project save(Project domain);

    Project update(Project domain);

    void delete(Long id);
}
