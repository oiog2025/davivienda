package co.com.davivienda.application.usecases;

import co.com.davivienda.application.mappers.UserMapper;
import co.com.davivienda.application.ports.input.UserInPort;
import co.com.davivienda.application.ports.output.UserOutPort;
import co.com.davivienda.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserUseCaseImp implements UserInPort {

    private final UserOutPort userOutPort;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userMapper.toDomainFromDto(userOutPort.findByEmail(email).get()));
    }

}
