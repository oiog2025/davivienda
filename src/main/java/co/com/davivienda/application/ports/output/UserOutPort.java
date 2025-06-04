
package co.com.davivienda.application.ports.output;


import co.com.davivienda.application.dtos.UserDto;

import java.util.Optional;

public interface UserOutPort {

    Optional<UserDto> findByEmail(String email);

}
