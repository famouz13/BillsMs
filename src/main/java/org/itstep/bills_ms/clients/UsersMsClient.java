package org.itstep.bills_ms.clients;

import org.itstep.bills_ms.models.external.UserDto;

public interface UsersMsClient {

    UserDto getUserById(Long id);

    UserDto getUserByToken(String token);
}
