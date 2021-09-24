package org.itstep.bills_ms.models.external;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String secondName;

    List<Role> roles;
}
