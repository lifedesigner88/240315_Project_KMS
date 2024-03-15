package org.example.team_kms.authority.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.team_kms.user.domain.User;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AuthorityGroupUserId implements Serializable {

    private AuthorityGroup authorityGroup;
    private User user;

}
