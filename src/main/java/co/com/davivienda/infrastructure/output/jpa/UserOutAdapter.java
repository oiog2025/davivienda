
package co.com.davivienda.infrastructure.output.jpa;

import co.com.davivienda.application.dtos.UserDto;
import co.com.davivienda.application.mappers.UserMapper;
import co.com.davivienda.application.ports.output.UserOutPort;
import co.com.davivienda.infrastructure.output.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserOutAdapter implements UserOutPort {

    private final UserRepository userRepository;
    private final UserMapper mapper;

     @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(mapper::toDtoFromEntity);
    }
}
