package co.com.davivienda.application.ports.input;


import co.com.davivienda.domain.User;

import java.util.Optional;

public interface UserInPort {
    Optional<User> findByEmail(String email);
}
