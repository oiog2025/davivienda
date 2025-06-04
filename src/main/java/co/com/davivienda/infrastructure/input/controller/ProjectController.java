package co.com.davivienda.infrastructure.input.controller;


import co.com.davivienda.application.dtos.ProjectDto;
import co.com.davivienda.application.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Endpoint para obtener un proyecto por su ID.
     * GET /api/v1/projects/{id}
     *
     * @param id El ID del proyecto.
     * @return ResponseEntity con el ProjectDto si se encuentra, o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto project = projectService.findById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para obtener un proyecto por su nombre.
     * GET /api/v1/projects/by-name?name={name}
     *
     * @param name El nombre del proyecto.
     * @return ResponseEntity con el ProjectDto si se encuentra, o 404 Not Found.
     */
    @GetMapping("/by-name")
    public ResponseEntity<ProjectDto> getProjectByName(@RequestParam String name) {
        ProjectDto project = projectService.findByName(name);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para obtener todos los proyectos.
     * GET /api/v1/projects
     *
     * @return ResponseEntity con una lista de ProjectDto.
     */
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para crear un nuevo proyecto.
     * POST /api/v1/projects
     *
     * @param projectDto El DTO del proyecto a crear (en el cuerpo de la petición).
     * @return ResponseEntity con el ProjectDto creado y estado 201 Created.
     */
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.save(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    /**
     * Endpoint para actualizar un proyecto existente.
     * PUT /api/v1/projects/{id}
     *
     * @param id El ID del proyecto a actualizar (desde la ruta).
     * @param projectDto El DTO con los datos actualizados (en el cuerpo de la petición).
     * @return ResponseEntity con el ProjectDto actualizado si se encuentra, o 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProject = projectService.findById(id);
        if (updatedProject != null) {
            updatedProject.setName(projectDto.getName());
            updatedProject.setDescription(projectDto.getDescription());
            updatedProject.setStartDate(projectDto.getStartDate());
            updatedProject.setEndDate(projectDto.getEndDate());
            updatedProject.setStatus(projectDto.getStatus());
            projectService.update(updatedProject);
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar un proyecto por su ID.
     * DELETE /api/v1/projects/{id}
     *
     * @param id El ID del proyecto a eliminar.
     * @return ResponseEntity con estado 204 No Content si la eliminación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
