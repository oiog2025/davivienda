package co.com.davivienda.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.davivienda.application.dtos.ProjectDto;
import co.com.davivienda.application.dtos.ProjectDto.ProjectDtoBuilder;
import co.com.davivienda.application.enums.ProjectStatus;
import co.com.davivienda.application.mappers.ProjectMapper;
import co.com.davivienda.application.ports.output.ProjectOutPort;
import co.com.davivienda.domain.Project;
import co.com.davivienda.domain.Project.ProjectBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectUseCaseImp.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class ProjectUseCaseImpTest {
    @MockitoBean
    private ProjectMapper projectMapper;

    @MockitoBean
    private ProjectOutPort projectOutPort;

    @Autowired
    private ProjectUseCaseImp projectUseCaseImp;

    /**
     * Test {@link ProjectUseCaseImp#findById(Long)}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#findById(Long)}
     */
    @Test
    @DisplayName("Test findById(Long)")
    void testFindById() {
        // Arrange
        ProjectDtoBuilder descriptionResult = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult = descriptionResult.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult = nameResult.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectOutPort.findById(Mockito.<Long>any())).thenReturn(buildResult);
        ProjectBuilder descriptionResult2 = Project.builder().description("The characteristics of someone or something");
        LocalDate endDate = LocalDate.of(1970, 1, 1);
        ProjectBuilder nameResult2 = descriptionResult2.endDate(endDate).id(1L).name("Name");
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        Project buildResult2 = nameResult2.startDate(startDate).status("Status").build();
        when(projectMapper.toDomainFromDto(Mockito.<ProjectDto>any())).thenReturn(buildResult2);

        // Act
        Optional<Project> actualFindByIdResult = projectUseCaseImp.findById(1L);

        // Assert
        verify(projectMapper).toDomainFromDto(isA(ProjectDto.class));
        verify(projectOutPort).findById(eq(1L));
        Project getResult = actualFindByIdResult.get();
        LocalDate endDate2 = getResult.getEndDate();
        assertEquals("1970-01-01", endDate2.toString());
        LocalDate startDate2 = getResult.getStartDate();
        assertEquals("1970-01-01", startDate2.toString());
        assertEquals("Name", getResult.getName());
        assertEquals("Status", getResult.getStatus());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(1L, getResult.getId().longValue());
        assertTrue(actualFindByIdResult.isPresent());
        assertSame(endDate, endDate2);
        assertSame(startDate, startDate2);
    }

    /**
     * Test {@link ProjectUseCaseImp#findByName(String)}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#findByName(String)}
     */
    @Test
    @DisplayName("Test findByName(String)")
    void testFindByName() {
        // Arrange
        ProjectDtoBuilder descriptionResult = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult = descriptionResult.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult = nameResult.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectOutPort.findByName(Mockito.<String>any())).thenReturn(buildResult);
        ProjectBuilder descriptionResult2 = Project.builder().description("The characteristics of someone or something");
        LocalDate endDate = LocalDate.of(1970, 1, 1);
        ProjectBuilder nameResult2 = descriptionResult2.endDate(endDate).id(1L).name("Name");
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        Project buildResult2 = nameResult2.startDate(startDate).status("Status").build();
        when(projectMapper.toDomainFromDto(Mockito.<ProjectDto>any())).thenReturn(buildResult2);

        // Act
        Optional<Project> actualFindByNameResult = projectUseCaseImp.findByName("Name");

        // Assert
        verify(projectMapper).toDomainFromDto(isA(ProjectDto.class));
        verify(projectOutPort).findByName(eq("Name"));
        Project getResult = actualFindByNameResult.get();
        LocalDate endDate2 = getResult.getEndDate();
        assertEquals("1970-01-01", endDate2.toString());
        LocalDate startDate2 = getResult.getStartDate();
        assertEquals("1970-01-01", startDate2.toString());
        assertEquals("Name", getResult.getName());
        assertEquals("Status", getResult.getStatus());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(1L, getResult.getId().longValue());
        assertTrue(actualFindByNameResult.isPresent());
        assertSame(endDate, endDate2);
        assertSame(startDate, startDate2);
    }

    /**
     * Test {@link ProjectUseCaseImp#findAll()}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#findAll()}
     */
    @Test
    @DisplayName("Test findAll()")
    void testFindAll() {
        // Arrange
        when(projectOutPort.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Project> actualFindAllResult = projectUseCaseImp.findAll();

        // Assert
        verify(projectOutPort).findAll();
        assertTrue(actualFindAllResult.isEmpty());
    }

    /**
     * Test {@link ProjectUseCaseImp#save(Project)}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#save(Project)}
     */
    @Test
    @DisplayName("Test save(Project)")
    void testSave() {
        // Arrange
        ProjectDtoBuilder descriptionResult = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult = descriptionResult.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult = nameResult.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectOutPort.save(Mockito.<ProjectDto>any())).thenReturn(buildResult);
        ProjectDtoBuilder descriptionResult2 = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult2 = descriptionResult2.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult2 = nameResult2.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectMapper.toDtoFromDomain(Mockito.<Project>any())).thenReturn(buildResult2);
        ProjectBuilder descriptionResult3 = Project.builder().description("The characteristics of someone or something");
        LocalDate endDate = LocalDate.of(1970, 1, 1);
        ProjectBuilder nameResult3 = descriptionResult3.endDate(endDate).id(1L).name("Name");
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        Project buildResult3 = nameResult3.startDate(startDate).status("Status").build();
        when(projectMapper.toDomainFromDto(Mockito.<ProjectDto>any())).thenReturn(buildResult3);

        // Act
        Project actualSaveResult = projectUseCaseImp.save(new Project());

        // Assert
        verify(projectMapper).toDomainFromDto(isA(ProjectDto.class));
        verify(projectMapper).toDtoFromDomain(isA(Project.class));
        verify(projectOutPort).save(isA(ProjectDto.class));
        LocalDate endDate2 = actualSaveResult.getEndDate();
        assertEquals("1970-01-01", endDate2.toString());
        LocalDate startDate2 = actualSaveResult.getStartDate();
        assertEquals("1970-01-01", startDate2.toString());
        assertEquals("Name", actualSaveResult.getName());
        assertEquals("Status", actualSaveResult.getStatus());
        assertEquals("The characteristics of someone or something", actualSaveResult.getDescription());
        assertEquals(1L, actualSaveResult.getId().longValue());
        assertSame(endDate, endDate2);
        assertSame(startDate, startDate2);
    }

    /**
     * Test {@link ProjectUseCaseImp#update(Project)}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#update(Project)}
     */
    @Test
    @DisplayName("Test update(Project)")
    void testUpdate() {
        // Arrange
        ProjectDtoBuilder descriptionResult = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult = descriptionResult.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult = nameResult.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectOutPort.update(Mockito.<ProjectDto>any())).thenReturn(buildResult);
        ProjectDtoBuilder descriptionResult2 = ProjectDto.builder()
                .description("The characteristics of someone or something");
        ProjectDtoBuilder nameResult2 = descriptionResult2.endDate(LocalDate.of(1970, 1, 1)).id(1L).name("Name");
        ProjectDto buildResult2 = nameResult2.startDate(LocalDate.of(1970, 1, 1)).status(ProjectStatus.PLANIFICADO).build();
        when(projectMapper.toDtoFromDomain(Mockito.<Project>any())).thenReturn(buildResult2);
        ProjectBuilder descriptionResult3 = Project.builder().description("The characteristics of someone or something");
        LocalDate endDate = LocalDate.of(1970, 1, 1);
        ProjectBuilder nameResult3 = descriptionResult3.endDate(endDate).id(1L).name("Name");
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        Project buildResult3 = nameResult3.startDate(startDate).status("Status").build();
        when(projectMapper.toDomainFromDto(Mockito.<ProjectDto>any())).thenReturn(buildResult3);

        // Act
        Project actualUpdateResult = projectUseCaseImp.update(new Project());

        // Assert
        verify(projectMapper).toDomainFromDto(isA(ProjectDto.class));
        verify(projectMapper).toDtoFromDomain(isA(Project.class));
        verify(projectOutPort).update(isA(ProjectDto.class));
        LocalDate endDate2 = actualUpdateResult.getEndDate();
        assertEquals("1970-01-01", endDate2.toString());
        LocalDate startDate2 = actualUpdateResult.getStartDate();
        assertEquals("1970-01-01", startDate2.toString());
        assertEquals("Name", actualUpdateResult.getName());
        assertEquals("Status", actualUpdateResult.getStatus());
        assertEquals("The characteristics of someone or something", actualUpdateResult.getDescription());
        assertEquals(1L, actualUpdateResult.getId().longValue());
        assertSame(endDate, endDate2);
        assertSame(startDate, startDate2);
    }

    /**
     * Test {@link ProjectUseCaseImp#delete(Long)}.
     * <p>
     * Method under test: {@link ProjectUseCaseImp#delete(Long)}
     */
    @Test
    @DisplayName("Test delete(Long)")
    void testDelete() {
        // Arrange
        doNothing().when(projectOutPort).delete(Mockito.<Long>any());

        // Act
        projectUseCaseImp.delete(1L);

        // Assert
        verify(projectOutPort).delete(eq(1L));
    }
}
