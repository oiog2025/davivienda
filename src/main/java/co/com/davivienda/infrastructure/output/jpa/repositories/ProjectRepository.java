
package co.com.davivienda.infrastructure.output.jpa.repositories;

import co.com.davivienda.infrastructure.output.jpa.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByName(String name);
    Optional<ProjectEntity> findById(Long id);
}
