package co.com.davivienda.application.usecases;

import co.com.davivienda.application.dtos.UserDto;
import co.com.davivienda.application.dtos.UserDto.UserDtoBuilder;
import co.com.davivienda.application.mappers.UserMapper;
import co.com.davivienda.application.ports.output.UserOutPort;
import co.com.davivienda.domain.User;
import co.com.davivienda.domain.User.UserBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserUseCaseImp.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class UserUseCaseImpTest {
    @MockitoBean
    private UserMapper userMapper;

    @MockitoBean
    private UserOutPort userOutPort;

    @Autowired
    private UserUseCaseImp userUseCaseImp;

    /**
     * Test {@link UserUseCaseImp#findByEmail(String)}.
     * <ul>
     *   <li>Then return {@link Optional#get()} CreatedAt toLocalTime toString is {@code 00:00}.</li>
     * </ul>
     * <p>
     * Method under test: {@link UserUseCaseImp#findByEmail(String)}
     */
    @Test
    @DisplayName("Test findByEmail(String); then return get() CreatedAt toLocalTime toString is '00:00'")
    void testFindByEmail_thenReturnGetCreatedAtToLocalTimeToStringIs0000() {
        // Arrange
        UserDtoBuilder builderResult = UserDto.builder();
        UserDtoBuilder passwordResult = builderResult.createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .email("jane.doe@example.org")
                .id(1L)
                .name("Name")
                .password("iloveyou");
        UserDto buildResult = passwordResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
        Optional<UserDto> ofResult = Optional.of(buildResult);
        when(userOutPort.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        UserBuilder builderResult2 = User.builder();
        LocalDate ofResult2 = LocalDate.of(1970, 1, 1);
        UserBuilder passwordResult2 = builderResult2.createdAt(ofResult2.atStartOfDay())
                .email("jane.doe@example.org")
                .id(1L)
                .name("Name")
                .password("iloveyou");
        LocalDate ofResult3 = LocalDate.of(1970, 1, 1);
        User buildResult2 = passwordResult2.updatedAt(ofResult3.atStartOfDay()).build();
        when(userMapper.toDomainFromDto(Mockito.<UserDto>any())).thenReturn(buildResult2);

        // Act
        Optional<User> actualFindByEmailResult = userUseCaseImp.findByEmail("jane.doe@example.org");

        // Assert
        verify(userMapper).toDomainFromDto(isA(UserDto.class));
        verify(userOutPort).findByEmail(eq("jane.doe@example.org"));
        User getResult = actualFindByEmailResult.get();
        LocalDateTime createdAt = getResult.getCreatedAt();
        LocalTime toLocalTimeResult = createdAt.toLocalTime();
        assertEquals("00:00", toLocalTimeResult.toString());
        LocalDate toLocalDateResult = createdAt.toLocalDate();
        assertEquals("1970-01-01", toLocalDateResult.toString());
        LocalDateTime updatedAt = getResult.getUpdatedAt();
        LocalDate toLocalDateResult2 = updatedAt.toLocalDate();
        assertEquals("1970-01-01", toLocalDateResult2.toString());
        assertEquals("Name", getResult.getName());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertNull(getResult.getIsActive());
        assertEquals(1L, getResult.getId().longValue());
        assertTrue(actualFindByEmailResult.isPresent());
        assertSame(toLocalTimeResult, updatedAt.toLocalTime());
        assertSame(ofResult2, toLocalDateResult);
        assertSame(ofResult3, toLocalDateResult2);
    }
}
