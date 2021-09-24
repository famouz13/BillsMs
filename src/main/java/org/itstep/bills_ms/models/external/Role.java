package org.itstep.bills_ms.models.external;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Role implements GrantedAuthority {

    private Long roleId;
    private String role;


    @Override
    public String getAuthority() {
        return role;
    }
}
